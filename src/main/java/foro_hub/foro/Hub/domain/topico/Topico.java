package foro_hub.foro.Hub.domain.topico;

import foro_hub.foro.Hub.domain.autor.Autor;
import foro_hub.foro.Hub.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String titulo;

    @Size(min = 10, message = "El mensaje debe tener al menos 10 caracteres")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private String status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
