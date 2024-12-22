provider "aws" {
  region = data.terraform_remote_state.global.outputs.aws_region
}

terraform {
  required_version = "~> 1.10.3"

  backend "s3" {
    region = "us-east-1"
    bucket = "fiap-9soat-snackbar"
    key    = "network/vpc/terraform.tfstate"
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
