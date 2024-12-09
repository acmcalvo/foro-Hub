package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.topico.Topico;
import foro_hub.foro.Hub.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")  // Proteger con Bearer Token
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para obtener los tópicos paginados
    @GetMapping
    public Page<Topico> obtenerTopicosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return topicoService.obtenerTopicosPaginados(page, size);
    }

    // Endpoint para eliminar un tópico por ID
    @DeleteMapping("/{id}")
    public void eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
    }
}
