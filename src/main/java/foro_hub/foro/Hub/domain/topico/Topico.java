package foro_hub.foro.Hub.domain.topico;


import foro_hub.foro.Hub.domain.autor.Autor;
import foro_hub.foro.Hub.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "topicos")
public class Topico {
    private static final int MIN_MESSAGE_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String titulo;

    @Size(min = MIN_MESSAGE_LENGTH, message = "El mensaje debe tener al menos " + MIN_MESSAGE_LENGTH + " caracteres")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime creationDate = LocalDateTime.now();

    private String status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
