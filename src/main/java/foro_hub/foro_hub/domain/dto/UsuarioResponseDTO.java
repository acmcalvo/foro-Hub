package foro_hub.foro_hub.domain.dto;



public class UsuarioResponseDTO {
    private Long id;
    private String login;

    public UsuarioResponseDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}