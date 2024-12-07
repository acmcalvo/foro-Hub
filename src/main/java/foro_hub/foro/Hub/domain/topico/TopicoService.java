package foro_hub.foro.Hub.domain.topico;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Método para obtener un tópico por ID
    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    // Método para actualizar un tópico
    public Topico actualizarTopico(Topico topico) {
        return topicoRepository.save(topico); // Guarda el tópico actualizado
    }
}
