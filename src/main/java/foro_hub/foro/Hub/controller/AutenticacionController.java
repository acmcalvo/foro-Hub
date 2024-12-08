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
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody DatosAutenticacionUsuario datosAutenticacionUsuario) {
        // Autenticación del usuario
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.getLogin(),
                datosAutenticacionUsuario.getClave()
        );
        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Generar el JWT token
        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Retornar el token en la respuesta
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
