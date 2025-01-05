#!/bin/bash

# Definicão de variáveis
REGION="us-east-1"
export AWS_DEFAULT_REGION=$REGION

# Variáveis AWS
AWS_DIR=~/.aws
AWS_CREDENTIALS_FILE=~/.aws/credentials
PROFILE="default"

#1. Função para configurar AWS CLI, caso não esteja configurado
if [ ! -d "$AWS_DIR" ]; then
    echo "Diretório ~/.aws não existe. Criando o diretório..."
    mkdir -p "$AWS_DIR"
    echo "Diretório ~/.aws criado com sucesso!"
else
    echo "Diretório ~/.aws já existe."
fi

if grep -q "^[^#]*\[$PROFILE\]" "$AWS_CREDENTIALS_FILE"; then
    echo "Credenciais AWS já configuradas para o perfil $PROFILE."
else
    echo "Credenciais AWS não encontradas. Vamos configurá-las."
    read -p "Digite sua aws_access_key_id (Obrigatório): " AWS_ACCESS_KEY_ID
    read -p "Digite sua aws_secret_access_key (Obrigatório): " AWS_SECRET_ACCESS_KEY
    echo
    read -p "Digite sua aws_session_token (Opcional, pressione Enter para pular): " AWS_SESSION_TOKEN

    {
        echo "[$PROFILE]"
        echo "aws_access_key_id = $AWS_ACCESS_KEY_ID"
        echo "aws_secret_access_key = $AWS_SECRET_ACCESS_KEY"
        if [ -n "$AWS_SESSION_TOKEN" ]; then
            echo "aws_session_token = $AWS_SESSION_TOKEN"
        fi
    } >> "$AWS_CREDENTIALS_FILE"

    echo "Credenciais AWS configuradas com sucesso!"
fi

#2. Função para provisionar ambiente EKS com TERRAFORM
apply_terraform() {
    DIR=$1
    echo "Iniciando Terraform no diretório: $DIR"
    cd $DIR || exit
    terraform init
    terraform destroy -auto-approve
    cd - || exit
}

# 2.1 Criação do Bucket S3 (storage/s3)
apply_terraform "$(pwd)/infra/terraform/$REGION/storage/s3"

# 2.2 Criação das Configurações Globais (global)
apply_terraform "$(pwd)/infra/terraform/$REGION/global"

# 2.3 Criação da VPC (network/vpc)
apply_terraform "$(pwd)/infra/terraform/$REGION/network/vpc"

# 2.4 Criação dos Security Groups (compute/securitygroup)
apply_terraform "$(pwd)/infra/terraform/$REGION/compute/securitygroup"

# 2.5 Criação do Cluster EKS (compute/eks/mithrandir)
apply_terraform "$(pwd)/infra/terraform/$REGION/compute/eks/mithrandir"

# 2.6 Configuração do Cluster EKS (compute/eks/mithrandir)
echo "Configurando o Cluster EKS..."
aws eks update-kubeconfig --region us-east-1 --name snackbar-mithrandir
echo "Cluster EKS configurado com sucesso!"

# 2.7 Criar um namespace chamado ns-snackbar
echo "Criando o namespace ns-snackbar..."
kubectl create namespace ns-snackbar
echo "Namespace ns-snackbar criado com sucesso!"

# 2.8 Aplicar o Helm Chart snackbar-mongo-db
echo "Instalando o Helm Chart snackbar-mongo-db..."
helm install snackbar-mongo-db $(pwd)/infra/helm-chart/mongo-custom --namespace ns-snackbar
echo "Helm Chart snackbar-mongo-db instalado com sucesso!"

# 2.9 Aplicar o Helm Chart snackbar
echo "Instalando o Helm Chart snackbar..."
helm install snackbar $(pwd)/infra/helm-chart/snackbar --namespace ns-snackbar
echo "Helm Chart snackbar instalado com sucesso!"

echo "Automatização dos recursos AWS completada com sucesso!"

