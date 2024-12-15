package foro_hub.foro_hub.domain.model.roles;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Find an existing role by its name, or create a new one if it doesn't exist.
     */
    public Rol getOrCreateRole(String roleName) {
        // Attempt to find the role by its name
        Optional<Rol> roleOptional = rolRepository.findByNombre(roleName);

        // If it doesn't exist, create and save a new one
        return roleOptional.orElseGet(() -> {
            Rol newRole = new Rol();
            newRole.setNombre(roleName);
            return rolRepository.save(newRole); // Persist new role
        });
    }
}