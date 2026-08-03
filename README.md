# Gestão de Ativos TI

Sistema para gerenciamento de ativos de Tecnologia da Informação desenvolvido com **Angular** no frontend e **Spring Boot** no backend, utilizando **PostgreSQL** como banco de dados e totalmente containerizado com Docker Compose.

O projeto foi estruturado seguindo uma arquitetura de serviços independentes, permitindo a evolução futura com novos componentes como mensageria, workers, armazenamento de arquivos, cache e outros serviços auxiliares.

---

# Arquitetura

A aplicação é composta pelos seguintes serviços:

| Serviço                | Tecnologia                    | Porta |
| ---------------------- | ----------------------------- | ----- |
| Frontend               | Angular + PrimeNG             | 4200  |
| Backend                | Spring Boot + Java 21 + Maven | 8080  |
| Banco de Dados         | PostgreSQL 17                 | 5432  |
| Administração do Banco | PgAdmin                       | 8081  |

Cada serviço possui seu próprio ambiente Docker, permitindo isolamento, manutenção independente e escalabilidade futura.

---

# Pré-requisitos

* [Docker](https://docs.docker.com/get-docker/) instalado
* [Docker Compose](https://docs.docker.com/compose/) instalado
* Git instalado

---

# Tecnologias

| Camada                | Tecnologia              |
| --------------------- | ----------------------- |
| Frontend              | Angular 21 + PrimeNG 21 |
| UI Components         | PrimeNG                 |
| Backend               | Spring Boot 3.2.4       |
| Linguagem Backend     | Java 21                 |
| Gerenciamento Backend | Maven                   |
| Banco de Dados        | PostgreSQL 17           |
| Administração Banco   | PgAdmin 4               |
| Containerização       | Docker + Docker Compose |
| Controle de Versão    | Git                     |

---

# Configuração inicial

## 1. Clonar o projeto

```bash
git clone https://github.com/seu-usuario/gestao-ativosti.git

cd gestao-ativosti
```

---

## 2. Configurar variáveis de ambiente

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

Ajuste as variáveis conforme necessário.

Exemplo:

```env
POSTGRES_DB=ativosti_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=root

PGADMIN_DEFAULT_EMAIL=admin@email.com
PGADMIN_DEFAULT_PASSWORD=root
```

---

# Executando o ambiente de desenvolvimento

O ambiente de desenvolvimento utiliza:

* Hot reload Angular
* Execução do Spring Boot via Maven
* Volumes compartilhados
* Cache do Maven
* Persistência do PostgreSQL

Execute:

```bash
docker compose \
-f docker-compose.yml \
-f docker-compose.dev.yml \
up --build
```

Após iniciar:

Frontend:

```
http://localhost:4200
```

Backend:

```
http://localhost:8080
```

Health Check:

```
http://localhost:8080/actuator/health
```

PgAdmin:

```
http://localhost:8081
```

---

# Executando ambiente padrão

Para executar somente os containers isolados:

```bash
docker compose up --build -d
```

Verificar containers:

```bash
docker ps
```

Ver logs:

```bash
docker compose logs -f
```

Parar aplicação:

```bash
docker compose down
```

Remover volumes e dados persistidos:

```bash
docker compose down -v
```

---

# Estrutura do Projeto

```plaintext
.
├── backend/
│   ├── Dockerfile.prod
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── ativosti/
│           │           └── AtivostiApplication.java
│           └── resources/
│               ├── application.properties
│               └── db/
│                   └── migration/
│
├── frontend/
│   ├── Dockerfile.prod
│   ├── angular.json
│   ├── package.json
│   └── src/
│       ├── app/
│       ├── index.html
│       ├── main.ts
│       └── styles.scss
│
├── docker-compose.yml
├── docker-compose.dev.yml
├── .devcontainer/
│   ├── devcontainer.json
│   └── Dockerfile
│
├── .env.example
├── .gitignore
└── README.md
```

---

# Ambiente de desenvolvimento

O projeto possui suporte para desenvolvimento utilizando Dev Containers do VS Code.

O container de desenvolvimento fornece:

* Java 21
* Maven
* Node.js 22
* Angular CLI
* Extensões VS Code para Angular e Java

Isso permite que o desenvolvimento seja realizado sem necessidade de instalar todas as dependências diretamente no sistema operacional.

---

# Banco de Dados

O PostgreSQL utiliza volume persistente Docker:

```
postgres_data
```

Os dados permanecem disponíveis mesmo após reiniciar os containers.

O gerenciamento pode ser realizado através do PgAdmin:

```
http://localhost:8081
```

---

# Próximas evoluções previstas

A arquitetura permite adicionar novos serviços independentes, como:

* Workers assíncronos
* RabbitMQ
* Kafka
* Redis
* MinIO para armazenamento de arquivos
* Serviços de autenticação
* Monitoramento e observabilidade

Novos serviços podem ser adicionados ao Docker Compose sem alterar os serviços existentes.

---

# Licença

Este projeto está sob a licença MIT.
