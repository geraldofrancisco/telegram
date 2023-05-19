CREATE SCHEMA IF NOT EXISTS telegram AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS telegram.bot (
	nome varchar NOT NULL PRIMARY KEY,
	token_acesso varchar NOT NULL,
	usuario_id varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS telegram.chat (
	nome varchar NOT NULL PRIMARY KEY,
	nome_bot varchar NOT NULL,
	CONSTRAINT chat_bot_fk FOREIGN KEY (nome_bot) REFERENCES telegram.bot(nome)
);

CREATE TABLE IF NOT EXISTS telegram.mensagem (
	id varchar PRIMARY KEY NULL DEFAULT gen_random_uuid(),
	chat_id varchar NOT NULL,
	mensagem text NULL,
	tipo varchar(10) NOT NULL,
	data_hora_criacao timestamp NOT NULL,
	data_hora_envio timestamp NULL,
	CONSTRAINT mensagem_chat_fk FOREIGN KEY (chat_id) REFERENCES telegram.chat(nome)
);
