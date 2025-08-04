ALTER TABLE topicos
    ADD CONSTRAINT uq_titulo_mensaje UNIQUE (titulo, mensaje);