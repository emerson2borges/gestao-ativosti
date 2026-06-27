-- ============================================
-- V2__populate_initial_data.sql
-- Popula tabelas de domínio com dados iniciais
-- ============================================

-- ----------------------------------------------------------------------
-- 1. CIDADES
-- ----------------------------------------------------------------------
INSERT INTO CIDADES (nome)
SELECT 'Campina Grande'
WHERE NOT EXISTS (SELECT 1 FROM CIDADES WHERE nome = 'Campina Grande');

INSERT INTO CIDADES (nome)
SELECT 'João Pessoa'
WHERE NOT EXISTS (SELECT 1 FROM CIDADES WHERE nome = 'João Pessoa');

-- ----------------------------------------------------------------------
-- 2. LOCALIZACOES
-- ----------------------------------------------------------------------
INSERT INTO LOCALIZACOES (cidade_id, tipo_ponto, nome_ponto)
SELECT c.id, 'Sede', 'Centro'
FROM CIDADES c
WHERE c.nome = 'Campina Grande'
  AND NOT EXISTS (
    SELECT 1 FROM LOCALIZACOES l
    WHERE l.cidade_id = c.id AND l.tipo_ponto = 'Sede' AND l.nome_ponto = 'Centro'
  );

INSERT INTO LOCALIZACOES (cidade_id, tipo_ponto, nome_ponto)
SELECT c.id, 'Casa da Cidadania', 'Alto Branco'
FROM CIDADES c
WHERE c.nome = 'Campina Grande'
  AND NOT EXISTS (
    SELECT 1 FROM LOCALIZACOES l
    WHERE l.cidade_id = c.id AND l.tipo_ponto = 'Casa da Cidadania' AND l.nome_ponto = 'Alto Branco'
  );

-- ----------------------------------------------------------------------
-- 3. TIPOS_ATIVO
-- ----------------------------------------------------------------------
INSERT INTO TIPOS_ATIVO (nome) VALUES
    ('Computador'),
    ('Impressora'),
    ('Switch'),
    ('Servidor'),
    ('Monitor'),
    ('Telefone')
ON CONFLICT (nome) DO NOTHING;

-- ----------------------------------------------------------------------
-- 4. PERFIS
-- ----------------------------------------------------------------------
INSERT INTO PERFIS (nome) VALUES
    ('Coordenador'),
    ('Técnico Regional'),
    ('Visualizador Agência')
ON CONFLICT (nome) DO NOTHING;

-- ----------------------------------------------------------------------
-- 5. PERMISSOES
-- ----------------------------------------------------------------------
INSERT INTO PERMISSOES (chave, descricao) VALUES
    -- Ativos
    ('ativo:listar', 'Listar ativos'),
    ('ativo:criar', 'Criar ativos'),
    ('ativo:atualizar', 'Atualizar ativos'),
    ('ativo:excluir', 'Excluir ativos'),
    -- Localizações
    ('localizacao:listar', 'Listar localizações'),
    ('localizacao:criar', 'Criar localizações'),
    ('localizacao:atualizar', 'Atualizar localizações'),
    ('localizacao:excluir', 'Excluir localizações'),
    -- Cidades
    ('cidade:listar', 'Listar cidades'),
    ('cidade:criar', 'Criar cidades'),
    ('cidade:atualizar', 'Atualizar cidades'),
    ('cidade:excluir', 'Excluir cidades'),
    -- Usuários
    ('usuario:listar', 'Listar usuários'),
    ('usuario:criar', 'Criar usuários'),
    ('usuario:atualizar', 'Atualizar usuários'),
    ('usuario:excluir', 'Excluir usuários'),
    -- Perfis
    ('perfil:listar', 'Listar perfis'),
    ('perfil:atualizar', 'Atualizar perfis')
ON CONFLICT (chave) DO NOTHING;

-- ----------------------------------------------------------------------
-- 6. PERFIL_PERMISSAO (associações)
-- ----------------------------------------------------------------------
-- Coordenador (perfil_id 1) → todas as permissões
INSERT INTO PERFIL_PERMISSAO (perfil_id, permissao_id)
SELECT 1, id FROM PERMISSOES
ON CONFLICT ON CONSTRAINT perfil_permissao_pkey DO NOTHING;

-- Técnico Regional (perfil_id 2) → permissões básicas (listar, criar, atualizar, excluir? sem excluir)
INSERT INTO PERFIL_PERMISSAO (perfil_id, permissao_id)
SELECT 2, id FROM PERMISSOES
WHERE chave IN (
    'ativo:listar', 'ativo:criar', 'ativo:atualizar',
    'localizacao:listar', 'localizacao:criar', 'localizacao:atualizar',
    'cidade:listar', 'cidade:criar', 'cidade:atualizar',
    'usuario:listar',
    'perfil:listar'
)
ON CONFLICT ON CONSTRAINT perfil_permissao_pkey DO NOTHING;

-- Visualizador Agência (perfil_id 3) → apenas leitura
INSERT INTO PERFIL_PERMISSAO (perfil_id, permissao_id)
SELECT 3, id FROM PERMISSOES
WHERE chave IN (
    'ativo:listar',
    'localizacao:listar',
    'cidade:listar',
    'usuario:listar'
)
ON CONFLICT ON CONSTRAINT perfil_permissao_pkey DO NOTHING;

-- ----------------------------------------------------------------------
-- 7. USUARIOS (usuário admin)
-- ----------------------------------------------------------------------
-- Senha: admin (hash BCrypt gerado com cost 10)
INSERT INTO USUARIOS (perfil_id, nome, matricula, email, senha)
VALUES (
    1,                           -- perfil Coordenador
    'Administrador do Sistema',
    'ADMIN',
    'admin@admin.com',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'
)
ON CONFLICT (email) DO NOTHING;

-- ----------------------------------------------------------------------
-- 8. ORDENS_COMPRA (opcional, apenas exemplo)
-- ----------------------------------------------------------------------
INSERT INTO ORDENS_COMPRA (numero_oc, data_compra, fornecedor, arquivo_path)
VALUES ('OC-2026/0001', '2026-01-15', 'Dell Tecnologia do Brasil', '/uploads/oc/oc_0001.pdf')
ON CONFLICT (numero_oc) DO NOTHING;