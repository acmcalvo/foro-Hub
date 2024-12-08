package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.usuario.DatosAutenticacionUsuario;
import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.infra.security.DatosJWTToken;
import foro_hub.foro.Hub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid DatosAutenticacionUsuario credentials) {
        // Crear el token de autenticación usando las credenciales
        Authentication authToken = new UsernamePasswordAuthenticationToken(credentials.getLogin(),
                credentials.getClave());

        // Autenticar al usuario
        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Generar el token JWT
        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Devolver el token en un formato estructurado
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
