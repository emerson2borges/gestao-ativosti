FROM node:22-alpine
WORKDIR /app

# Copia os arquivos de dependências primeiro (otimiza o cache do Docker)
COPY package*.json ./
RUN npm install

# Copia o restante do código do front-end
# COPY . .
EXPOSE 4200

# Roda o servidor permitindo conexões externas e na porta padrão
CMD ["npx", "ng", "serve", "--host", "0.0.0.0", "--poll", "2000"]