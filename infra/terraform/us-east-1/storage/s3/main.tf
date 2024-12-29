resource "aws_s3_bucket" "fiap_snackbar_bucket" {
  bucket = "fiap-9soat-snackbar"
  acl    = "private"
}
