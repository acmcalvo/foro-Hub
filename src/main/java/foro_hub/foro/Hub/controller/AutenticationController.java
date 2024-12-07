package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.usuario.DatosValidarUsuario;
import foro_hub.foro.Hub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DatosValidarUsuario datos) {
        // Simula la validación del usuario (deberías conectarlo con tu base de datos)
        boolean isValidUser = datos.username().equals("admin") && datos.password().equals("password");

        if (isValidUser) {
            String token = tokenService.generateToken(datos.username());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
