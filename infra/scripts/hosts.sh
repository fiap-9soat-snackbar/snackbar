#!/bin/bash

# Nome dos serviços Kubernetes
SERVICE_API="snackbar-lb"
SERVICE_DB="snackbar-mongo-db-lb"

# Função para obter o IP ou Hostname de um serviço Kubernetes
get_external_ip_or_hostname() {
  local service_name=$1
  local external_ip=$(kubectl get svc $service_name --output jsonpath='{.status.loadBalancer.ingress[0].ip}')
  local external_hostname=$(kubectl get svc $service_name --output jsonpath='{.status.loadBalancer.ingress[0].hostname}')
  
  # Priorizar o IP, se não existir, usar o Hostname
  if [ -n "$external_ip" ]; then
    echo "$external_ip"
  elif [ -n "$external_hostname" ]; then
    echo "$external_hostname"
  else
    echo "N/A"
  fi
}

# Obter os External IPs ou Hostnames dos serviços
IP_API=$(get_external_ip_or_hostname $SERVICE_API)
IP_DB=$(get_external_ip_or_hostname $SERVICE_DB)

# Verificar se os IPs ou hostnames foram encontrados e exibir a mensagem correspondente
if [ "$IP_API" == "N/A" ]; then
  echo "Erro: External IP ou Hostname do serviço $SERVICE_API não encontrado."
else
  echo "External IP/Hostname do serviço $SERVICE_API encontrado: $IP_API"
fi

if [ "$IP_DB" == "N/A" ]; then
  echo "Erro: External IP ou Hostname do serviço $SERVICE_DB não encontrado."
else
  echo "External IP/Hostname do serviço $SERVICE_DB encontrado: $IP_DB"
fi

# Exibir mensagem para o usuário adicionar as entradas no /etc/hosts
echo ""
echo "Estes são os LoadBalancers criados, por favor adicione as seguintes entradas ao seu /etc/hosts:"
echo ""
echo " $IP_API    api.snackbar.com.br"
echo " $IP_DB     db.snackbar.com.br"
echo ""
echo "Para adicionar, execute como root (no Linux) ou abra o arquivo 'hosts' como administrador no Windows."
