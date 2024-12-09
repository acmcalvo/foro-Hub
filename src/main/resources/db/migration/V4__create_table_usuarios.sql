CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,           -- Identificador único para el usuario
    login VARCHAR(255) UNIQUE NOT NULL,             -- Campo para el login, único y no nulo
    clave VARCHAR(255) NOT NULL,                 -- Campo para la contraseña, no nulo
    email VARCHAR(255) UNIQUE,                      -- Campo para el email, único
    nombre_completo VARCHAR(255),                   -- Campo para el nombre completo
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Fecha de creación con valor por defecto (fecha y hora actual)
    roles VARCHAR(255) DEFAULT 'USER'                -- Campo para el rol del usuario, valor por defecto 'USER'
);
