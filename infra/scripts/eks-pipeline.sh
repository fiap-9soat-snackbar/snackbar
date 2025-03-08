#!/bin/bash

# Definicão de variáveis
REGION="us-east-1"

#2. Função para provisionar ambiente EKS com TERRAFORM
apply_terraform() {
    DIR=$1
    echo "Iniciando Terraform no diretório: $DIR"
    cd $DIR || exit
    terraform init
    terraform apply -auto-approve
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

# 2.7 Configuracão do Storage Class
echo "Configurando o Storage Class..."
kubectl patch storageclass gp2 -p '{"metadata": {"annotations":{"storageclass.kubernetes.io/is-default-class":"true"}}}'

# 2.8 Criar um namespace chamado ns-snackbar
echo "Criando o namespace ns-snackbar..."
kubectl create namespace ns-snackbar
echo "Namespace ns-snackbar criado com sucesso!"

# 2.8.1. Logar no namespace ns-snackbar
echo "Entrando no namespace ns-snackbar..."
kubectl config set-context --current --namespace=ns-snackbar

# 2.9 Aplicar o Helm Chart mongodb
echo "Instalando o Helm Chart mongodb..."
helm install mongodb $(pwd)/infra/helm-chart/mongodb --namespace ns-snackbar
echo "Helm Chart mongodb instalado com sucesso!"

# 2.10 Aplicar o Helm Chart snackbar
echo "Instalando o Helm Chart snackbar..."
helm install snackbar $(pwd)/infra/helm-chart/snackbar --namespace ns-snackbar
echo "Helm Chart snackbar instalado com sucesso!"

# 2.11 Aplicar o Helm Chart Metric Server utilizado para monitoramento do cluster
echo "Instalando o Helm Chart Metrics Server..."
helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/
helm install metrics-server metrics-server/metrics-server
echo "Helm Chart Metrics Server instalado com sucesso!"

echo ""
echo "Ajustando as configurações de réplica do MongoDB..."
sleep 90
kubectl exec -it mongodb-1 -c mongodb-c -- mongosh --username root --password rootpassword --eval "rs.initiate({ _id: 'rs0', members: [ { _id: 0, host: 'mongodb-0.mongodb-clusterip.ns-snackbar.svc.cluster.local:27017' }, { _id: 1, host: 'mongodb-1.mongodb-clusterip.ns-snackbar.svc.cluster.local:27017' } ] });"


echo "Automatização dos recursos AWS completada com sucesso!"
echo "Aguarde enquanto os LoadBalancers são provisionados..."  
sleep 60 
echo ""  

#./infra/scripts/host.sh
