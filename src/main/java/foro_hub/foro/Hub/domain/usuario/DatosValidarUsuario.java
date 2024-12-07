package foro_hub.foro.Hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosValidarUsuario(
        @NotBlank String username,
        @NotBlank String password
) {
}
