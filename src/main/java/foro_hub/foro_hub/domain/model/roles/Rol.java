package foro_hub.foro_hub.domain.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Rol implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre; // Example values: "ROLE_USER", "ROLE_ADMIN"

    @Override
    public String getAuthority() {
        return this.nombre;
    }
}