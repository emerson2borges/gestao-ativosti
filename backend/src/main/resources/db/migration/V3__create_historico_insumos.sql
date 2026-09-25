CREATE TABLE IF NOT EXISTS HISTORICO_INSUMOS (
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