package foro_hub.foro.Hub.exception;

public class TopicoNotFoundException extends RuntimeException {

    public TopicoNotFoundException(Long id) {
        super("No se encontró el tópico con el ID: " + id);
    }
}