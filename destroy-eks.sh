#!/bin/bash

# Definir a região AWS
REGION="us-east-1"
export AWS_DEFAULT_REGION=$REGION

# Função para executar terraform destroy
destroy_terraform() {
    DIR=$1
    echo "Iniciando Terraform destroy no diretório: $DIR"
    cd $DIR || exit
    terraform destroy -auto-approve
    cd - || exit
}

# 1. Remover o Helm Chart snackbar
echo "Removendo o Helm Chart snackbar..."
helm uninstall snackbar --namespace ns-snackbar
echo "Helm Chart snackbar removido com sucesso!"

# 2. Remover o Helm Chart snackbar-mongo-db
echo "Removendo o Helm Chart snackbar-mongo-db..."
helm uninstall snackbar-mongo-db --namespace ns-snackbar
echo "Helm Chart snackbar-mongo-db removido com sucesso!"

# 3. Remover o namespace ns-snackbar
echo "Removendo o namespace ns-snackbar..."
kubectl delete namespace ns-snackbar
echo "Namespace ns-snackbar removido com sucesso!"

# 4. Remover as configurações do Cluster EKS
echo "Removendo configurações do Cluster EKS..."
aws eks update-kubeconfig --region us-east-1 --name snackbar-mithrandir --force
echo "Configurações do Cluster EKS removidas com sucesso!"

# 5. Remover o Cluster EKS (compute/eks/mithrandir)
destroy_terraform "infra/terraform/$REGION/compute/eks/mithrandir"

# 6. Remover os Security Groups (compute/securitygroup)
destroy_terraform "infra/terraform/$REGION/compute/securitygroup"

# 7. Remover a VPC (network/vpc)
destroy_terraform "infra/terraform/$REGION/network/vpc"

# 8. Remover as Configurações Globais (global)
destroy_terraform "infra/terraform/$REGION/global"

# 9. limpar bucket s3 fiap-9soat-snackbar com aws cli
echo "Limpando o bucket s3 fiap-9soat-snackbar..."
aws s3 rm s3://fiap-9soat-snackbar --recursive
echo "Bucket s3 fiap-9soat-snackbar limpo com sucesso!"

# 10. Remover o Bucket S3 (storage/s3)
destroy_terraform "infra/terraform/$REGION/storage/s3"

echo "Remoção dos recursos AWS e Kubernetes completada com sucesso!"
