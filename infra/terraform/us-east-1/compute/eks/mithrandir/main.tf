module "eks_mithrandir" {
  source                          = "terraform-aws-modules/eks/aws"
  version                         = "18.27.1"
  vpc_id                          = data.terraform_remote_state.vpc.outputs.vpc_id
  cluster_name                    = join("-", [data.terraform_remote_state.global.outputs.project_name, "mithrandir"])
  cluster_version                 = "1.31"
  subnet_ids                      = data.terraform_remote_state.vpc.outputs.private_subnets
  cluster_security_group_id       = data.terraform_remote_state.security_group_mithrandir.outputs.sg_additional_mithrandir_eks_id
  node_security_group_id          = data.terraform_remote_state.security_group_mithrandir.outputs.sg_node_mithrandir_eks_id
  create_cluster_security_group   = false
  create_node_security_group      = false
  cluster_endpoint_public_access  = true
  cluster_endpoint_private_access = true
  create_iam_role                 = "false"
  enable_irsa                     = "false"
  iam_role_arn                    = "arn:aws:iam::208016918243:role/LabRole"  
  tags = {
    Provisioned  = "Terraform"
    CreatedBy    = "Team-82"
    Product      = "SnackBar"
  }

  #cluster_addons = {
  #  coredns = {
  #    #resolve_conflicts_on_update = "PRESERVE"
  #    #resolve_conflicts_on_create = "OVERWRITE"
  #    #service_account_role_arn    = "arn:aws:iam::208016918243:role/LabRole"
  #    #version                     = "v1.11.3-eksbuild.1"
  #  }
  #  aws-ebs-csi-driver = {
  #    #resolve_conflicts_on_update = "PRESERVE"
  #    #resolve_conflicts_on_create = "OVERWRITE"
  #    #service_account_role_arn    = "arn:aws:iam::208016918243:role/LabRole"
  #    #version                     = "v1.37.0-eksbuild.1"
  #  }
  #  kube-proxy = {}
  #  vpc-cni = {
  #   #resolve_conflicts_on_update = "PRESERVE"
  #   #resolve_conflicts_on_create = "OVERWRITE"
  #   #service_account_role_arn    = "arn:aws:iam::208016918243:role/LabRole"
  #   #version                     = "v1.19.0-eksbuild.1"
  #  }
  #}

  
  # EKS Managed Node Group(s)
  eks_managed_node_group_defaults = {
    ami_type       = "AL2_x86_64"
    disk_size      = 50
    instance_types = ["t2.micro", "t3.medium"]
  }

  eks_managed_node_groups = {
    snackbar = {
      name                    = "ng-snackbar-app"
      min_size                = 1
      max_size                = 3
      desired_size            = 1
      instance_types          = ["t3.medium"]
      key_name                = "eks-key-pair"
      create_iam_role         = "false"
      iam_role_arn            = "arn:aws:iam::208016918243:role/LabRole"  

      labels = {
        application = "snackbar"
      }

      taints = {
        dedicated = {
          key    = "application"
          value  = "snackbar-app"
          effect = "NO_SCHEDULE"
        }
      }

      tags = {
        Provisioned  = "Terraform"
        CreatedBy    = "Team-82"
        Product      = "SnackBar"
      }

      block_device_mappings = {
        xvda = {
          device_name = "/dev/xvda"
          ebs = {
            volume_size           = 30
            volume_type           = "gp3"
            iops                  = 3000
            throughput            = 125
            encrypted             = false
            delete_on_termination = true
          }
        }
      }
    }

    database = {
      name                    = "ng-database"
      min_size                = 1
      max_size                = 3
      desired_size            = 1
      instance_types          = ["t3.medium"]
      key_name                = "eks-key-pair"
      create_iam_role         = "false"
      iam_role_arn            = "arn:aws:iam::208016918243:role/LabRole" 
      labels = {
        application = "mongodb"
      }

      taints = {
        dedicated = {
          key    = "application"
          value  = "mongodb"
          effect = "NO_SCHEDULE"
        }
      }

      tags = {
        Provisioned  = "Terraform"
        CreatedBy    = "Team-82"
        Product      = "SnackBar"
      }

      block_device_mappings = {
        xvda = {
          device_name = "/dev/xvda"
          ebs = {
            volume_size           = 30
            volume_type           = "gp3"
            iops                  = 3000
            throughput            = 125
            encrypted             = false
            delete_on_termination = true
          }
        }
      }
    }

  }
}

# Manage aws-auth ConfigMap
resource "kubernetes_config_map_v1_data" "aws_auth" {
metadata {
    name      = "aws-auth"
    namespace = "kube-system"
}

data = {
    mapRoles = yamlencode([
    {
        rolearn  = "arn:aws:iam::208016918243:role/LabRole"
        username = "eks-bastion-role"
        groups   = ["system:masters"]
    },
    {
        rolearn  = module.eks_mithrandir.eks_managed_node_groups["snackbar"].iam_role_arn
        username = "system:node:{{EC2PrivateDNSName}}"
        groups   = ["system:bootstrappers", "system:nodes"]
    },
    {
        rolearn  = module.eks_mithrandir.eks_managed_node_groups["database"].iam_role_arn
        username = "system:node:{{EC2PrivateDNSName}}"
        groups   = ["system:bootstrappers", "system:nodes"]
    }
    ])
}

force = true
}

# Node Group Scale Up
resource "aws_autoscaling_policy" "eks_autoscaling_policy-up" {
  count = length(module.eks_mithrandir.eks_managed_node_groups)

  name                   = "${module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]}-eks_autoscaling_policy-up"
  scaling_adjustment     = 1
  adjustment_type        = "ChangeInCapacity"
  cooldown               = 300
  autoscaling_group_name = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]
}

resource "aws_cloudwatch_metric_alarm" "eks-node-group-up" {
  count = length(module.eks_mithrandir.eks_managed_node_groups)

  alarm_name          = "${module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]}-eks-node-group-up"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  evaluation_periods  = "1"
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = "300"
  statistic           = "Average"
  threshold           = "80"

  dimensions = {
    AutoScalingGroupName = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]
  }
  alarm_actions     = [aws_autoscaling_policy.eks_autoscaling_policy-up[count.index].arn]
  alarm_description = "This metric monitors the memory usage of the autoscaling group machines"
}

# Node Group Scale Down
resource "aws_autoscaling_policy" "eks_autoscaling_policy-down" {
  count = length(module.eks_mithrandir.eks_managed_node_groups)

  name                   = "${module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]}-eks_autoscaling_policy-down"
  scaling_adjustment     = -1
  adjustment_type        = "ChangeInCapacity"
  cooldown               = 300
  autoscaling_group_name = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]
}

resource "aws_cloudwatch_metric_alarm" "eks-node-group-down" {
  count = length(module.eks_mithrandir.eks_managed_node_groups)

  alarm_name          = "${module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]}-eks-node-group-down"
  comparison_operator = "LessThanOrEqualToThreshold"
  evaluation_periods  = "1"
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = "300"
  statistic           = "Average"
  threshold           = "80"

  dimensions = {
    AutoScalingGroupName = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names[count.index]
  }
  alarm_actions     = [aws_autoscaling_policy.eks_autoscaling_policy-down[count.index].arn]
  alarm_description = "This metric monitors the memory usage of the autoscaling group machines"
}