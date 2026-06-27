-- ============================================
-- V1__initial_schema.sql
-- Criação do esquema inicial (ordem de dependências)
-- ============================================

-- 1. Cidades (primeiro, sem dependências)
CREATE TABLE CIDADES (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 2. Localizações
CREATE TABLE LOCALIZACOES (
    id BIGSERIAL PRIMARY KEY,
    cidade_id BIGINT NOT NULL,
    tipo_ponto VARCHAR(50) NOT NULL,
    nome_ponto VARCHAR(100) NOT NULL,
    CONSTRAINT fk_localizacao_cidade FOREIGN KEY (cidade_id) REFERENCES CIDADES(id)
);

-- 3. Ordens de Compra
CREATE TABLE ORDENS_COMPRA (
    id BIGSERIAL PRIMARY KEY,
    numero_oc VARCHAR(50) NOT NULL UNIQUE,
    data_compra DATE NOT NULL,
    fornecedor VARCHAR(150) NOT NULL,
    arquivo_path VARCHAR(255) NULL
);

-- 4. Tipos de Ativo
CREATE TABLE TIPOS_ATIVO (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- 5. Ativos
CREATE TABLE ATIVOS (
    id BIGSERIAL PRIMARY KEY,
    tipo_id BIGINT NOT NULL,
    localizacao_id BIGINT NOT NULL,
    ordem_compra_id BIGINT NULL,
    patrimonio VARCHAR(50) NOT NULL UNIQUE,
    hostname_atual VARCHAR(50) NULL,
    responsavel VARCHAR(100) NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Estoque Sede',
    CONSTRAINT fk_ativo_tipo FOREIGN KEY (tipo_id) REFERENCES TIPOS_ATIVO(id),
    CONSTRAINT fk_ativo_localizacao FOREIGN KEY (localizacao_id) REFERENCES LOCALIZACOES(id),
    CONSTRAINT fk_ativo_ordem_compra FOREIGN KEY (ordem_compra_id) REFERENCES ORDENS_COMPRA(id)
);

-- 6. Subativos Internos
CREATE TABLE SUBATIVOS_INTERNO (
    id BIGSERIAL PRIMARY KEY,
    ativo_id BIGINT NOT NULL,
    tipo_componente VARCHAR(50) NOT NULL,
    especificacao VARCHAR(150) NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    chamado_glpi VARCHAR(20) NULL,
    CONSTRAINT fk_subativo_ativo FOREIGN KEY (ativo_id) REFERENCES ATIVOS(id) ON DELETE CASCADE
);

-- 7. Estoque Insumos
CREATE TABLE ESTOQUE_INSUMOS (
    id BIGSERIAL PRIMARY KEY,
    ordem_compra_id BIGINT NULL,
    nome_item VARCHAR(100) NOT NULL,
    quantidade_total INT NOT NULL DEFAULT 0,
    quantidade_disponivel INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_estoque_ordem_compra FOREIGN KEY (ordem_compra_id) REFERENCES ORDENS_COMPRA(id)
);

-- 8. Controle de Acesso
CREATE TABLE PERFIS (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE PERMISSOES (
    id BIGSERIAL PRIMARY KEY,
    chave VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE PERFIL_PERMISSAO (
    perfil_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    PRIMARY KEY (perfil_id, permissao_id),
    CONSTRAINT fk_perfil_permissao_perfil FOREIGN KEY (perfil_id) REFERENCES PERFIS(id) ON DELETE CASCADE,
    CONSTRAINT fk_perfil_permissao_permissao FOREIGN KEY (permissao_id) REFERENCES PERMISSOES(id) ON DELETE CASCADE
);

CREATE TABLE USUARIOS (
    id BIGSERIAL PRIMARY KEY,
    perfil_id BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    CONSTRAINT fk_usuario_perfil FOREIGN KEY (perfil_id) REFERENCES PERFIS(id)
);

-- 9. Históricos e Logs
CREATE TABLE HISTORICO_HOSTNAME (
    id BIGSERIAL PRIMARY KEY,
    ativo_id BIGINT NOT NULL,
    hostname_antigo VARCHAR(50) NULL,
    hostname_novo VARCHAR(50) NULL,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    motivo VARCHAR(255) NULL,
    chamado_glpi VARCHAR(20) NULL,
    CONSTRAINT fk_historico_hostname_ativo FOREIGN KEY (ativo_id) REFERENCES ATIVOS(id) ON DELETE CASCADE
);

CREATE TABLE HISTORICO_ATIVOS (
    id BIGSERIAL PRIMARY KEY,
    ativo_id BIGINT NOT NULL,
    campo_alterado VARCHAR(50) NOT NULL,
    valor_antigo VARCHAR(150) NULL,
    valor_novo VARCHAR(150) NULL,
    chamado_glpi VARCHAR(20) NULL,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_historico_ativo_ativo FOREIGN KEY (ativo_id) REFERENCES ATIVOS(id) ON DELETE CASCADE,
    CONSTRAINT fk_historico_ativo_usuario FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
);

CREATE TABLE HISTORICO_INSUMOS (
    id BIGSERIAL PRIMARY KEY,
    insumo_id BIGINT NOT NULL,
    tipo_movimentacao VARCHAR(10) NOT NULL,
    localizacao_id BIGINT NULL,
    quantidade INT NOT NULL,
    chamado_glpi VARCHAR(20) NULL,
    data_movimentacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_historico_insumo_insumo FOREIGN KEY (insumo_id) REFERENCES ESTOQUE_INSUMOS(id) ON DELETE CASCADE,
    CONSTRAINT fk_historico_insumo_localizacao FOREIGN KEY (localizacao_id) REFERENCES LOCALIZACOES(id),
    CONSTRAINT fk_historico_insumo_usuario FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
);

CREATE TABLE AUDITORIA (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    acao VARCHAR(10) NOT NULL,
    tabela_afetada VARCHAR(50) NOT NULL,
    registro_id BIGINT NOT NULL,
    detalhes TEXT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_auditoria_usuario FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
);