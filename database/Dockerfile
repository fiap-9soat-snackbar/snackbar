# Use a imagem oficial do MongoDB 8.0.1 como base
FROM mongo:8.0.1

# Copie o script de inicialização para o diretório de inicialização do MongoDB
COPY ./init-scripts/*.js /docker-entrypoint-initdb.d/

# Exponha a porta padrão do MongoDB
EXPOSE 27017
