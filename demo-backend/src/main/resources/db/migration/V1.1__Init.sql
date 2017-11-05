CREATE SEQUENCE demo.id_seq;

CREATE table demo.command (
	id bigint NOT NULL,
	CONSTRAINT command_pkey PRIMARY KEY (id)
)

