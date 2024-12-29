#!/bin/bash

# Definir a região AWS
REGION="us-east-1"
export AWS_DEFAULT_REGION=$REGION

# Função para executar terraform init e terraform apply
apply_terraform() {
    DIR=$1
    echo "Iniciando Terraform no diretório: $DIR"
    cd $DIR || exit
    terraform init
    terraform apply -auto-approve
    cd - || exit
}

# 1. Criação do Bucket S3 (storage/s3)
apply_terraform "infra/terraform/$REGION/storage/s3"

# 2. Criação das Configurações Globais (global)
apply_terraform "infra/terraform/$REGION/global"

# 3. Criação da VPC (network/vpc)
apply_terraform "infra/terraform/$REGION/network/vpc"

# 4. Criação dos Security Groups (compute/securitygroup)
apply_terraform "infra/terraform/$REGION/compute/securitygroup"

# 5. Criação do Cluster EKS (compute/eks/mithrandir)
apply_terraform "infra/terraform/$REGION/compute/eks/mithrandir"


# 6. Configuração do Cluster EKS (compute/eks/mithrandir)
echo "Configurando o Cluster EKS..."
aws eks update-kubeconfig --region us-east-1 --name snackbar-mithrandir
echo "Cluster EKS configurado com sucesso!"

# 7- Criar um namespace chamado ns-snackbar
echo "Criando o namespace ns-snackbar..."
kubectl create namespace ns-snackbar
echo "Namespace ns-snackbar criado com sucesso!"


# 8. Aplicar o Helm Chart snackbar-mongo-db
echo "Instalando o Helm Chart snackbar-mongo-db..."
helm install snackbar-mongo-db ./infra/helm-chart/mongo-custom --namespace ns-snackbar
echo "Helm Chart snackbar-mongo-db instalado com sucesso!"

# 9. Aplicar o Helm Chart snackbar
echo "Instalando o Helm Chart snackbar..."
helm install snackbar ./infra/helm-chart/snackbar --namespace ns-snackbar
echo "Helm Chart snackbar instalado com sucesso!"

echo "Automatização dos recursos AWS completada com sucesso!"

#TODO
# SET AWS ACCOUND ID ON TERRAFORM
# IMPLEMENT SERVICE ON HELM CHART
# CREATE AWS R53 ZONE
# CREATE AWS R53 RECORD TO SERVICE


