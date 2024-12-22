provider "aws" {
  region = data.terraform_remote_state.global.outputs.aws_region
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.mithrandir.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.mithrandir.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.mithrandir.token
}

terraform {
  required_version = "~> 1.10.3"

  backend "s3" {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "fiap-9soat-snackbar/compute/eks/mithrandir/terraform.tfstate"
  }
}

data "terraform_remote_state" "global" {
  backend = "s3"
  config = {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "fiap-9soat-snackbar/global/terraform.tfstate"
  }
}

data "terraform_remote_state" "vpc" {
  backend = "s3"
  config = {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "fiap-9soat-snackbar/network/vpc/terraform.tfstate"
  }
}

data "terraform_remote_state" "security_group_mithrandir" {
  backend = "s3"
  config = {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "fiap-9soat-snackbar/compute/securitygroup/eks/mithrandir/terraform.tfstate"
  }
}

### CLUSTER ID/AUTH ###
data "aws_eks_cluster" "mithrandir" {
  name = module.eks_mithrandir.cluster_id
}

data "aws_eks_cluster_auth" "mithrandir" {
  name = module.eks_mithrandir.cluster_id
}

### EKS NODEGROUPS ROLES ###
## GET NODE GROUPS NAMES FROM EKS CLUSTER MODULE ##
data "aws_iam_role" "role_name" {
  name = "LabRole"
}


