package foro_hub.foro.Hub.service;

import foro_hub.foro.Hub.domain.topico.Topico;
import foro_hub.foro.Hub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

//     Método para obtener todos los tópicos
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll(); // Devuelve una lista con todos los tópicos
    }

//   Método para obtener un tópico por su ID
    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id); // Devuelve un Optional del tópico encontrado por ID
    }

//Método para actualizar un tópico
    public Topico actualizarTopico(Topico topico) {
        return topicoRepository.save(topico); // Guarda el tópico actualizado en la base de datos
    }
}
