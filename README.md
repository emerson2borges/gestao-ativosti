# Gestão de Ativos TI

Projeto para gestão de ativos de TI com **Angular** (frontend) e **Spring Boot** (backend), totalmente containerizado com Docker Compose.

# Pré-requisitos
- [Docker](https://docs.docker.com/get-docker/) instalado
- [Docker Compose](https://docs.docker.com/compose/install/) instalado (já incluso no Docker Desktop)

# Tecnologias

| Camada              | Tecnologia                           |
|---------------------|--------------------------------------|
| **Frontend**        | Angular 21 + PrimeNG 21              |
| **Backend**         | Spring Boot 3.2.4 + Maven + Java 21  |
| **Banco de Dados**  | PostgreSQL 17                        |
| **Containerização** | Docker Compose                       |
 
# Configuração

1. **Clone o repositório**
   `git clone https//github.com/seu-usuario/gestao-ativosti.git`
   `cd gestao-ativosti`
2. Copie o arquivo `.env.example` para `.env` e ajuste se necessário.
3. **Executar os containers com o comando**
    `docker compose up --build -d`
4. **Verificar se os containers estão executando com os comandos**
    `docker ps`
    `docker compose logs`
5. **Parar os containers com o comando**
    `docker compose down`

# Acessos

- [Frontend] http://localhost:4200
- [Backend] (health): http://localhost:8080/actuator/health
- [PgAdmin] http://localhost:8081 (login conforme .env)

# Estrutura do Projeto

```plaintext
.
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── ativosti/
│           │           └── AtivostiApplication.java
│           └── resources/
│               └── application.properties
├── frontend/
│   ├── Dockerfile
│   ├── angular.json
│   ├── package.json
│   └── src/
│       ├── app/
│       │   ├── app.component.html
│       │   ├── app.component.scss
│       │   ├── app.component.ts
│       │   ├── app.config.ts
│       │   └── app.routes.ts
│       ├── index.html
│       ├── main.ts
│       └── styles.scss
├── docker-compose.yml
├── .env.example
├── .gitignore
└── README.md
```

# Licença
Este projeto está sob a licença MIT.