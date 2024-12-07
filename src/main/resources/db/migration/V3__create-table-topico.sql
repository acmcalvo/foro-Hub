CREATE TABLE IF NOT EXISTS topico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    mensaje TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    autor_id INT,
    curso_id INT,
    FOREIGN KEY (autor_id) REFERENCES autores(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
