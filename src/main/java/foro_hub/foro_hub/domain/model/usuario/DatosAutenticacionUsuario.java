package foro_hub.foro_hub.domain.model.usuario;

import jakarta.validation.constraints.NotBlank;

public class DatosAutenticacionUsuario {

    @NotBlank(message = "El campo login no puede estar vacío.")
    private String login;

    @NotBlank(message = "El campo clave no puede estar vacío.")
    private String clave;
    // Constructor
    public DatosAutenticacionUsuario(String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    // Getters y setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
