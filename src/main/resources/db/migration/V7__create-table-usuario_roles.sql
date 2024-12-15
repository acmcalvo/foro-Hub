   CREATE TABLE usuario_roles (
       usuario_id BIGINT NOT NULL,
       rol_id BIGINT NOT NULL,
       PRIMARY KEY (usuario_id, rol_id),
       FOREIGN KEY (usuario_id) REFERENCES usuario (id),
       FOREIGN KEY (rol_id) REFERENCES rol (id)
   );