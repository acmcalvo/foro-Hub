CREATE TABLE Topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ABIERTO', 'CERRADO') NOT NULL,
    autor_nombre VARCHAR(50) NOT NULL,
    autor_email VARCHAR(255) NOT NULL,
    curso_id BIGINT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);