FROM mcr.microsoft.com/devcontainers/universal:2

USER root

RUN useradd -m -s /bin/bash vscode \
    && echo "vscode ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER vscode