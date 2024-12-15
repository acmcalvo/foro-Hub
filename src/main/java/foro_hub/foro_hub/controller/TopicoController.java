package foro_hub.foro_hub.controller;

import foro_hub.foro_hub.domain.curso.Curso;
import foro_hub.foro_hub.domain.curso.CursoRepository;
import foro_hub.foro_hub.domain.dto.TopicoRequestDTO;
import foro_hub.foro_hub.domain.dto.TopicoResponseDTO;
import foro_hub.foro_hub.domain.model.usuario.Usuario;
import foro_hub.foro_hub.domain.model.usuario.UsuarioRepository;
import foro_hub.foro_hub.domain.topico.Topico;
import foro_hub.foro_hub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> registrarTopico(@Valid @RequestBody TopicoRequestDTO request) {
        Usuario autor = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensaje(request.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setStatus(Topico.Status.ABIERTO);

        Topico topicoGuardado = topicoRepository.save(topico);

        return ResponseEntity.ok(new TopicoResponseDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getFechaCreacion(),
                topicoGuardado.getStatus().name(),
                autor.getNombre(),
                curso.getNombre()
        ));
    }
}
