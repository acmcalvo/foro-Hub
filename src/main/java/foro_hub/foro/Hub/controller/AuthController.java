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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/authenticate")  // Cambio de la ruta aquí
    public ResponseEntity<DatosJWTToken> authenticateUser(@RequestBody DatosAutenticacionUsuario authenticationData) {
        // Autenticación del usuario
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                authenticationData.getLogin(),
                authenticationData.getClave()
        );

        // Autenticación del usuario
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);

        // Obtener el usuario autenticado (UserDetails -> Usuario)
        Usuario usuarioAutenticado = (Usuario) authenticatedUser.getPrincipal();

        // Generar el JWT token
        String jwtToken = tokenService.generateToken(usuarioAutenticado);

        // Retornar el token en la respuesta
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}
