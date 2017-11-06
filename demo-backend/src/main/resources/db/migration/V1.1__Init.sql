CREATE SEQUENCE demo.id_seq;

CREATE table demo.command_store (
	id bigint NOT NULL,
	payload jsonb NOT NULL,
	CONSTRAINT command_pkey PRIMARY KEY (id)
)

