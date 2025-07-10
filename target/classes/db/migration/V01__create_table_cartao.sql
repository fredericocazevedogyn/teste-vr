CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE cartao (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    numero VARCHAR(16) NOT NULL UNIQUE CHECK (char_length(numero) = 16 AND numero ~ '^\d+$'),
    saldo NUMERIC(7,2) NOT NULL CHECK (saldo >= 0),
    senha VARCHAR(4) NOT NULL CHECK (senha ~ '^\d{4}$')
);