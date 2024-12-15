package foro_hub.foro_hub.domain.topico;

import foro_hub.foro_hub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Data
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotBlank(message = "El nombre del autor no puede estar vacío")
    private String autorNombre;

    @NotBlank(message = "El correo del autor no puede estar vacío")
    private String autorEmail;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public enum Status {
        ABIERTO, CERRADO
    }
}