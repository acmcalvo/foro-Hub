package foro_hub.foro_hub.controller;

import foro_hub.foro_hub.domain.curso.Curso;
import foro_hub.foro_hub.domain.curso.CursoRepository;
import foro_hub.foro_hub.domain.topico.Topico;
import foro_hub.foro_hub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository; // Repository for Curso

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody Topico topicoDetails) {
        // Fetch the course from the database (if needed)
        Curso curso = cursoRepository.findById(topicoDetails.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Create the Topico entity and set its fields
        Topico topico = new Topico();
        topico.setTitulo(topicoDetails.getTitulo());
        topico.setMensaje(topicoDetails.getMensaje());
        topico.setAutorNombre(topicoDetails.getAutorNombre()); // Use autorNombre
        topico.setAutorEmail(topicoDetails.getAutorEmail());   // Use autorEmail
        topico.setCurso(curso);

        // Save the topico
        Topico saved = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}