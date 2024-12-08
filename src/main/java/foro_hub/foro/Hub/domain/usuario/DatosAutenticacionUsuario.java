package foro_hub.foro.Hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public class DatosAutenticacionUsuario {

    @NotBlank
    private String login;

    @NotBlank
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
