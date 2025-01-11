#!/bin/bash

# 1. Removendo Deploy do Monikube
echo "Removendo Snackbar do Minikube..."
minikube delete

if [ $? -eq 0 ]; then
    echo "Snackbar removido com sucesso!"
else
    echo "Erro ao remover Snackbar."
fi