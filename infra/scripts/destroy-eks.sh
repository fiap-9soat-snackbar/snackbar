#!/bin/bash

# Definicão de Variáveis
REGION="us-east-1"
export AWS_DEFAULT_REGION=$REGION

#1. Função para remover ambiente EKS
destroy_terraform() {
    DIR=$1
    echo "Iniciando Terraform destroy no diretório: $DIR"
    cd $DIR || exit
    terraform destroy -auto-approve
    cd - || exit
}

# 1.1 Remover o Helm Chart snackbar
echo "Removendo o Helm Chart snackbar..."
helm uninstall snackbar --namespace ns-snackbar
echo "Helm Chart snackbar removido com sucesso!"

# 1.2 Remover o Helm Chart mongodb-headless
echo "Removendo o Helm Chart mongodb..."
helm uninstall mongodb --namespace ns-snackbar
echo "Helm Chart mongodb removido com sucesso!"

# 1.3 Remover o namespace ns-snackbarsd
#echo "Removendo o namespace ns-snackbar..."
#kubectl delete namespace ns-snackbar
#echo "Namespace ns-snackbar removido com sucesso!"

# 1.4 Remover as configurações do Cluster EKS
echo "Removendo configurações do Cluster EKS..."
aws eks update-kubeconfig --region us-east-1 --name snackbar-mithrandir --force
echo "Configurações do Cluster EKS removidas com sucesso!"

# 1.5 Remover o Cluster EKS (compute/eks/mithrandir)
destroy_terraform "$(pwd)/infra/terraform/$REGION/compute/eks/mithrandir"

# 1.6 Remover os Security Groups (compute/securitygroup)
destroy_terraform "$(pwd)/infra/terraform/$REGION/compute/securitygroup"

# 1.7 Remover a VPC (network/vpc)
destroy_terraform "$(pwd)/infra/terraform/$REGION/network/vpc"

# 1.8 Remover as Configurações Globais (global)
destroy_terraform "$(pwd)/infra/terraform/$REGION/global"

# 1.9 limpar bucket s3 fiap-9soat-snackbar com aws cli
echo "Limpando o bucket s3 fiap-9soat-snackbar..."
aws s3 rm s3://fiap-9soat-snackbar --recursive
echo "Bucket s3 fiap-9soat-snackbar limpo com sucesso!"

# 1.10 Remover o Bucket S3 (storage/s3)
destroy_terraform "$(pwd)/infra/terraform/$REGION/storage/s3"

echo "Remoção dos recursos AWS e Kubernetes completada com sucesso!"
