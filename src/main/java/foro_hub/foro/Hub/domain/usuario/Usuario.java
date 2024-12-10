package foro_hub.foro.Hub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String clave;

    // Lista de roles del usuario almacenados como una cadena separada por comas
    private String roles;  // Se almacena como una cadena

    // Este método proporciona los roles del usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir la cadena de roles en una lista de SimpleGrantedAuthority
        if (roles == null || roles.isEmpty()) {
            return List.of(); // Retorna una lista vacía si no hay roles definidos
        }

        return List.of(roles.split(","))
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.trim())) // El trim elimina los espacios extra
                .toList();
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
