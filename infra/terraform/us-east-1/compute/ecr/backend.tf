provider "aws" {
  region = data.terraform_remote_state.global.outputs.aws_region
}

terraform {
  required_version = ">= 1.0"

    backend "s3" {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "compute/ecr/mithrandir/terraform.tfstate"
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 5.61"
    }
  }
}


data "terraform_remote_state" "global" {
  backend = "s3"
  config = {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "global/terraform.tfstate"
  }
}

locals {
  region = "us-east-1"
  name   = "ecr-ex-${replace(basename(path.cwd), "_", "-")}"

  account_id = data.aws_caller_identity.current.account_id

  tags = {
    Name       = local.name
    Example    = local.name
    Repository = "https://github.com/terraform-aws-modules/terraform-aws-ecr"
  }
}

data "aws_caller_identity" "current" {}
