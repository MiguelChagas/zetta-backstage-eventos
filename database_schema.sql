-- Script de Criação do Banco de Dados - Zetta Backstage
-- Banco de Dados: PostgreSQL

-- 1. Tabela de Usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela de Eventos
CREATE TABLE IF NOT EXISTS eventos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_evento DATE NOT NULL,
    localizacao VARCHAR(255),
    usuario_id INTEGER NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- 3. Tabela de Itens de Checklist
CREATE TABLE IF NOT EXISTS itens_checklist (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDENTE', -- PENDENTE, CONCLUIDO
    prioridade VARCHAR(20) DEFAULT 'MEDIA', -- ALTA, MEDIA, BAIXA
    evento_id INTEGER NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (evento_id) REFERENCES eventos(id) ON DELETE CASCADE
);

-- Comentários para documentação
COMMENT ON TABLE usuarios IS 'Tabela que armazena os produtores de eventos cadastrados.';
COMMENT ON TABLE eventos IS 'Eventos criados por um usuário.';
COMMENT ON TABLE itens_checklist IS 'Tarefas associadas a um evento específico.';