package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.service.TopicoService;
import foro_hub.foro.Hub.domain.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para obtener los tópicos paginados
    @GetMapping("/topicos")
    public Page<Topico> obtenerTopicosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return topicoService.obtenerTopicosPaginados(page, size);
    }
}
