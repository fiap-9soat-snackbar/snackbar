output "eks_mithrandir_endpoint" {
  description = "Endpoint for EKS control plane."
  value       = module.eks_mithrandir.cluster_endpoint
}

output "eks_mithrandir_security_group_id" {
  description = "Security group ids attached to the cluster control plane."
  value       = module.eks_mithrandir.cluster_security_group_id
}

output "worker_eks_mithrandir_security_group_id" {
  description = "Security group ids attached to the workers."
  value       = module.eks_mithrandir.node_security_group_id
}

output "mithrandir_id" {
  description = "token for eks"
  value = module.eks_mithrandir.cluster_id
}

output "eks_managed_node_groups_autoscaling_group_names" {
  description = "ASG nodeGroups names"
  value = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names
}

output "Managed_groups" {
  value = module.eks_mithrandir.eks_managed_node_groups_autoscaling_group_names

}
