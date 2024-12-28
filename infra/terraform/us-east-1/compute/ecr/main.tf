################################################################################
# ECR Repository
################################################################################

module "ecr_disabled" {
  source = "terraform-aws-modules/ecr/aws"
  create = false
}

module "ecr" {
  source = "terraform-aws-modules/ecr/aws"

  repository_name = "snackbar"

  repository_read_write_access_arns = ["arn:aws:iam::208016918243:role/LabRole"]
  create_lifecycle_policy           = false
  repository_lifecycle_policy = jsonencode({
    rules = [
      {
        rulePriority = 1,
        description  = "Keep last 30 images",
        selection = {
          tagStatus     = "tagged",
          tagPrefixList = ["v"],
          countType     = "imageCountMoreThan",
          countNumber   = 30
        },
        action = {
          type = "expire"
        }
      }
    ]
  })

  repository_force_delete = true

   tags = {
    Provisioned  = "Terraform"
    CreatedBy    = "Team-82"
    Product      = "SnackBar"
  }
}

module "public_ecr" {
  source = "terraform-aws-modules/ecr/aws"

  repository_name = "snackbar-pub"
  repository_type = "public"

  repository_read_write_access_arns = ["arn:aws:iam::208016918243:role/LabRole"]

  public_repository_catalog_data = {
    description       = "Docker container for SnackBar App"
    #about_text        = file("${path.module}/files/ABOUT.md")
    #usage_text        = file("${path.module}/files/USAGE.md")
    operating_systems = ["Linux"]
    architectures     = ["x86"]
   # logo_image_blob   = filebase64("${path.module}/files/clowd.png")
  }

  tags = {
    Provisioned  = "Terraform"
    CreatedBy    = "Team-82"
    Product      = "SnackBar"
  }
}


