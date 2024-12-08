package foro_hub.foro.Hub.infra.security;

public class DatosJWTToken {

    private String token;

    // Constructor
    public DatosJWTToken(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }
}
