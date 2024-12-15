package foro_hub.foro_hub.domain.model.respuesta;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<Respuesta> registrarRespuesta(@Valid @RequestBody Respuesta nuevaRespuesta) {
        Respuesta respuestaGuardada = respuestaRepository.save(nuevaRespuesta);
        return ResponseEntity.ok(respuestaGuardada);
    }

    @GetMapping
    public ResponseEntity<List<Respuesta>> listarRespuestas() {
        return ResponseEntity.ok(respuestaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> obtenerRespuesta(@PathVariable Long id) {
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(id);
        return optionalRespuesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id, @RequestBody Respuesta respuestaDetails) {
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(id);
        if (optionalRespuesta.isPresent()) {
            Respuesta respuesta = optionalRespuesta.get();
            respuesta.setMensaje(respuestaDetails.getMensaje());
            respuestaRepository.save(respuesta);
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(id);
        if (optionalRespuesta.isPresent()) {
            respuestaRepository.deleteById(id);
            return ResponseEntity.ok("Respuesta eliminada correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

