#!/bin/bash

# 0- Start Minikube
echo "Iniciando o Minikube..."
minikube start

# 1- Setar o contexto Kubernetes para o Minikube
echo "Setando contexto Kubernetes para o Minikube..."
kubectl config use-context minikube

# 2- Criar um namespace chamado ns-snackbar
echo "Criando o namespace ns-snackbar..."
kubectl create namespace ns-snackbar

# 3- Logar no namespace ns-snackbar
echo "Entrando no namespace ns-snackbar..."
kubectl config set-context --current --namespace=ns-snackbar

# 4- Instalar o Helm Chart snackbar
echo "Instalando o Helm Chart snackbar..."
helm install snackbar ./helm-chart/snackbar --namespace ns-snackbar

# 5- Instalar o Helm Chart snackbar-mongo-db
echo "Instalando o Helm Chart snackbar-mongo-db..."
helm install snackbar-mongo-db ./helm-chart/mongo-custom --namespace ns-snackbar

echo "Aguardando 30 segundos para que os pods iniciem..."
sleep 30

# 6- Fazer kubectl port-forward para o serviço snackbar (porta 8080)
echo "Configurando port-forward para o serviço snackbar na porta 8080..."
kubectl port-forward service/snackbar 8080:80 --namespace ns-snackbar &

# 7- Fazer kubectl port-forward para o serviço snackbar-mongo-db (porta 27017)
echo "Configurando port-forward para o serviço snackbar-mongo-db na porta 27017..."
kubectl port-forward service/snackbar-mongo-db 27017:27017 --namespace ns-snackbar &

# 8- Listar todos os pods no namespace ns-snackbar
echo "Listando todos os pods no namespace ns-snackbar..."
kubectl get pods --namespace ns-snackbar
