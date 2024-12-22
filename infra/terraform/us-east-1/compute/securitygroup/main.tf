module "sg_additional_mithrandir_eks" {
  source = "terraform-aws-modules/security-group/aws"

  name        = join("-", [data.terraform_remote_state.global.outputs.project_name, "Additional-mithrandir-EKS"])
  description = join(":", [data.terraform_remote_state.global.outputs.project_name, "default additional mithrandir security group"])
  vpc_id      = data.terraform_remote_state.vpc.outputs.vpc_id


  ingress_cidr_blocks = [data.terraform_remote_state.vpc.outputs.vpc_cidr_block, "10.30.0.0/16"]
  #ingress_rules       = ["all-all"]

  ingress_with_self = [{
    rule        = "all-all"
    description = "Allow additional to communicate with self."
  }]


  egress_with_cidr_blocks = [
    {
      rule        = "all-all"
      cidr_blocks = "0.0.0.0/0"
    }
  ]

  tags = {
    Name         = join("-", [data.terraform_remote_state.global.outputs.project_name, "Additional-mithrandir-EKS"])
    Provisioned  = "Terraform"
    CreatedBy    = "Team-82"
    Product      = "SnackBar"
  }
}


module "sg_node_mithrandir_eks" {
  source = "terraform-aws-modules/security-group/aws"

  name        = join("-", [data.terraform_remote_state.global.outputs.project_name, "Node-mithrandir-EKS"])
  description = join(":", [data.terraform_remote_state.global.outputs.project_name, "default Node mithrandir security group"])
  vpc_id      = data.terraform_remote_state.vpc.outputs.vpc_id

  ingress_cidr_blocks = [data.terraform_remote_state.vpc.outputs.vpc_cidr_block, "10.30.0.0/16"]
  #ingress_rules       = ["all-all"]

  ingress_with_self = [{
    rule        = "all-all"
    description = "Allow node to communicate with each other."
  }]

  egress_with_cidr_blocks = [
    {
      rule        = "all-all"
      cidr_blocks = "0.0.0.0/0"
    }
  ]

  tags = {
    "kubernetes.io/cluster/mithrandir" = "owned" # <-- Importante para o service do k8s criar o loadbalance
    Name                                         = join("-", [data.terraform_remote_state.global.outputs.project_name, "Node-Cluster-1-prod-EKS"])
    Provisioned  = "Terraform"
    CreatedBy    = "Team-82"
    Product      = "SnackBar"
  }
}