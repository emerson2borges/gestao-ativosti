FROM mcr.microsoft.com/devcontainers/base:ubuntu

RUN apt-get update && apt-get install -y \
    git \
    curl \
    wget \
    unzip \
    zip \
    sudo \
    openjdk-21-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Node 22 LTS
RUN curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install -y nodejs

# Angular CLI
RUN npm install -g @angular/cli

USER vscode

WORKDIR /workspace