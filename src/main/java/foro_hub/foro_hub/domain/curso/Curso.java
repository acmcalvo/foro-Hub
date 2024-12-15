package foro_hub.foro_hub.domain.curso;


import foro_hub.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;
}
