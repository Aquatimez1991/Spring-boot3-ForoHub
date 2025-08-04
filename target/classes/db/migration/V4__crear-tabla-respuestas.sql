CREATE TABLE respuestas
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario     BIGINT NOT NULL,
    id_topico      BIGINT NOT NULL,
    mensaje        TEXT   NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    activo         TINYINT(1) DEFAULT 1,
    CONSTRAINT fk_respuesta_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (id_topico) REFERENCES topicos (id)
);