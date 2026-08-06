FROM node:22-alpine

WORKDIR /workspace/frontend

RUN addgroup -S vscode && adduser -S vscode -G vscode

COPY package*.json ./

RUN npm install

COPY . .

RUN chown -R vscode:vscode /workspace

USER vscode

EXPOSE 4200

CMD ["npm", "start", "--", "--host", "0.0.0.0"]