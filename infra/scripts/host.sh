#!/bin/bash

# Variáveis de Ambiente
CLUSTER_NAME="snackbar-mithrandir"

# Função para obter o EXTERNAL-IP ou DNS de um serviço do tipo LoadBalancer com base na label app
get_lb_dns() {
    local app_name=$1
    local lb_dns=$(kubectl get svc -l app="$app_name" -n ns-snackbar -o jsonpath='{.items[*].status.loadBalancer.ingress[*].hostname}')
    
    if [ -z "$lb_dns" ]; then
        lb_dns=$(kubectl get svc -l app="$app_name" -n ns-snackbar -o jsonpath='{.items[*].status.loadBalancer.ingress[*].ip}')
    fi

    echo "$lb_dns"
}

# Verificar se o cluster EKS existe
echo "Verificando o cluster EKS..."
aws eks describe-cluster --name "$CLUSTER_NAME" > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Erro: Cluster EKS '$CLUSTER_NAME' não encontrado ou não está acessível."
    exit 1
fi

# Obter o DNS ou EXTERNAL IP dos LoadBalancers atrelados ao MongoDB e Snackbar
echo "Obtendo o LoadBalancer do MongoDB..."
MONGODB_LB_DNS=$(get_lb_dns "mongodb")

echo "Obtendo o LoadBalancer do Snackbar..."
SNACKBAR_LB_DNS=$(get_lb_dns "snackbar")

# Exibir os resultados
echo "MongoDB Load Balancer DNS/External IP: $MONGODB_LB_DNS"
echo "Snackbar Load Balancer DNS/External IP: $SNACKBAR_LB_DNS"

# Verificar se foram encontrados LoadBalancers
if [ -z "$MONGODB_LB_DNS" ]; then
    echo "Aviso: Não foi encontrado um LoadBalancer para o MongoDB."
fi

if [ -z "$SNACKBAR_LB_DNS" ]; then
    echo "Aviso: Não foi encontrado um LoadBalancer para o Snackbar."
fi

# Exibir instruções para uso dos serviços
echo ""
echo "Por favor, para rodar o Mongo, utilize o endereço: $MONGODB_LB_DNS:27017"
echo "Para rodar a API do Snackbar, utilize: $SNACKBAR_LB_DNS"
echo "Link para Swagger: $SNACKBAR_LB_DNS/swagger-ui/index.html"