provider "aws" {
  region = data.terraform_remote_state.global.outputs.aws_region
}

terraform {
  required_version = "~> 1.10.3"

  backend "s3" {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "fiap-9soat-snackbar/compute/securitygroup/eks/mithrandir/terraform.tfstate"
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
