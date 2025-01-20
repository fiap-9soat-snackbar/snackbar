#!/bin/bash

#1. Aplicando permissão de execução aos scripts
chmod +x ./infra/scripts/destroy-minikube.sh
chmod +x ./infra/scripts/destroy-eks.sh

#2. Oferecer ao usuário como remover infraestrutura
echo "Escolha qual ambiente deseja remover:"
echo "1- Minikube"
echo "2- EKS"
read -p "Digite sua escolha (1 ou 2): " OPTION

case $OPTION in
    1)
        echo "Você escolheu Minikube."
        ./infra/scripts/destroy-minikube.sh
        ;;
    2)
        echo "Você escolheu EKS."
        ./infra/scripts/destroy-eks.sh
        ;;
    *)
        echo "Opção inválida. Por favor, escolha 1 ou 2."
        exit 1
        ;;
esac
