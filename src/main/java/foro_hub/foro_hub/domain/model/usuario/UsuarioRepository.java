package foro_hub.foro_hub.domain.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por login
    Optional<Usuario> findByLogin(String login);
}
