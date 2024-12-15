package foro_hub.foro_hub.domain.model.roles;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    // Find role by its name
    Optional<Rol> findByNombre(String nombre);
}