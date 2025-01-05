#!/bin/bash

#1. Função para verificar se o Docker está rodando
check_docker() {
    if ! docker info &> /dev/null; then
        echo "Docker não está rodando. Por favor, inicie o Docker antes de continuar."
        exit 1
    fi
}

check_docker
echo "Docker está rodando. Iniciando o Minikube..."

# 2. Limpar cache do Minikube
echo "Limpando cache do Minikube..."
minikube delete

# 3. Start Minikube
echo "Iniciando o Minikube..."
minikube start

# 4. Setar o contexto Kubernetes para o Minikube
echo "Setando contexto Kubernetes para o Minikube..."
kubectl config use-context minikube

# 5. Criar um namespace chamado ns-snackbar
echo "Criando o namespace ns-snackbar..."
kubectl create namespace ns-snackbar

# 6. Logar no namespace ns-snackbar
echo "Entrando no namespace ns-snackbar..."
kubectl config set-context --current --namespace=ns-snackbar

# 7. Instalar o Helm Chart snackbar-mongo-db
echo "Instalando o Helm Chart snackbar-mongo-db..."
helm install snackbar-mongo-db $(pwd)/infra/helm-chart/mongo-custom --namespace ns-snackbar --values $(pwd)/infra/helm-chart/mongo-custom/values-minikube.yaml

# 8. Instalar o Helm Chart snackbar
echo "Instalando o Helm Chart snackbar..."
helm install snackbar $(pwd)/infra/helm-chart/snackbar --namespace ns-snackbar --values $(pwd)/infra/helm-chart/snackbar/values-minikube.yaml

echo "Aguardando 30 segundos para que os pods iniciem..."
sleep 30

# 9. Listar todos os pods no namespace ns-snackbar
echo "Listando todos os pods no namespace ns-snackbar..."
kubectl get pods --namespace ns-snackbar

# 10. Fazer kubectl port-forward para o serviço snackbar (porta 8080)
echo "Configurando port-forward para o serviço snackbar na porta 8080 e snackbar-mongo-db na porta 27017..."
kubectl port-forward service/snackbar 8080:80 --namespace ns-snackbar && kubectl port-forward service/snackbar-mongo-db 27017:27017 --namespace ns-snackbar &

# 11. Fazer kubectl port-forward para o serviço snackbar-mongo-db (porta 27017)
#echo "Configurando port-forward para o serviço snackbar-mongo-db na porta 27017..."
#kubectl port-forward service/snackbar-mongo-db 27017:27017 --namespace ns-snackbar &



