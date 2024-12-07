package foro_hub.foro.Hub.service;

import foro_hub.foro.Hub.domain.topico.Topico;
import foro_hub.foro.Hub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Método para obtener todos los tópicos
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    // Método para obtener un tópico por su ID
    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    // Método para actualizar un tópico
    public Topico actualizarTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    // Método para obtener tópicos con paginación
    public Page<Topico> obtenerTopicosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return topicoRepository.findAll(pageable);
    }
}
