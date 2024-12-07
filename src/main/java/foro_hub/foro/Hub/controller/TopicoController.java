package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.topico.Topico;
import foro_hub.foro.Hub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para actualizar un tópico
    @PutMapping("/{id}")  // La ruta debe ser /topicos/{id}, no /topicos/topicos/{id}
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoActualizado) {
        // Buscar el tópico existente en la base de datos
        var topicoExistente = topicoService.obtenerTopicoPorId(id);

        if (topicoExistente.isPresent()) {
            // Si el tópico existe, lo actualizamos
            Topico topico = topicoExistente.get();
            // Actualizamos el tópico con los datos proporcionados
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            topico.setStatus(topicoActualizado.getStatus());
            // Delegamos al servicio para guardar el tópico actualizado
            Topico topicoGuardado = topicoService.actualizarTopico(topico);
            return ResponseEntity.ok(topicoGuardado); // Devuelven el tópico actualizado con el estado 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Si no existe, retornamos 404
        }
    }
}
