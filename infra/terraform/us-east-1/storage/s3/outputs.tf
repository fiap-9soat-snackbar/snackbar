output "snackbar_bucket_name" {
  value = aws_s3_bucket.fiap_snackbar_bucket.bucket
  description = "The name of the S3 bucket created for the snackbar project"
}