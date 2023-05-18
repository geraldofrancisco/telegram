CREATE SCHEMA IF NOT EXISTS telegram AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS telegram.bot (
	nome varchar NOT NULL PRIMARY KEY,
	token_acesso varchar NOT NULL,
	usuario_id varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS telegram.chat (
	nome varchar NULL,
	nome_bot varchar NULL,
	CONSTRAINT chat_bot_fk FOREIGN KEY (nome_bot) REFERENCES telegram.bot(nome)
);
