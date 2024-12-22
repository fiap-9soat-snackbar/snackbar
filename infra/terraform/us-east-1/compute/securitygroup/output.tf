## Additional Mithrandir

output "sg_additional_mithrandir_eks_id" {
  description = "The ID of the security group"
  value       = module.sg_additional_mithrandir_eks.security_group_id
}

output "sg_additional_mithrandir_eks_name" {
  description = "The name of the security group"
  value       = module.sg_additional_mithrandir_eks.security_group_name
}


## Node Mithrandir

output "sg_node_mithrandir_eks_id" {
  description = "The ID of the security group"
  value       = module.sg_node_mithrandir_eks.security_group_id
}

output "sg_node_mithrandir_eks_name" {
  description = "The name of the security group"
  value       = module.sg_node_mithrandir_eks.security_group_name
}