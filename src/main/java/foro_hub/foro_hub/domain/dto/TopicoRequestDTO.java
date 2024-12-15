package foro_hub.foro_hub.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotNull(message = "El ID del autor no puede ser nulo")
        Long autorId,

        @NotNull(message = "El ID del curso no puede ser nulo")
        Long cursoId
) {}