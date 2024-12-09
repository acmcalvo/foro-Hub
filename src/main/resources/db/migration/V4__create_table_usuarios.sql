CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,  -- Change 'username' to 'login'
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    nombre_completo VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(255) DEFAULT 'USER' -- Columna para el rol con valor por defecto 'USER'
);
