package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")  // Protect with Bearer Token
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@RequestBody Usuario user) {
        Usuario registeredUser = usuarioService.registrarUsuario(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Get a user by ID (requires Bearer Token)
    @GetMapping("/{userId}")
    public ResponseEntity<Usuario> getUser(@PathVariable Long userId) {
        Usuario user = usuarioService.obtenerUsuarioPorId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}