package foro_hub.foro.Hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para encontrar un usuario por su nombre de usuario

    Optional<Usuario> findByLogin(String login);

}
