package foro_hub.foro.Hub.controller;

import foro_hub.foro.Hub.domain.usuario.DatosAutenticacionUsuario;
import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.infra.security.DatosJWTToken;
import foro_hub.foro.Hub.infra.security.TokenService;
import jakarta.validation.Valid;
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
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication usuarioAutenticado = authenticationManager.authenticate(createAuthToken(datosAutenticacionUsuario));
        String jwtToken = generateJwtToken(usuarioAutenticado);
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

    private Authentication createAuthToken(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        return new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.getLogin(),
                datosAutenticacionUsuario.getClave()
        );
    }

    // Cambiar el nombre de la función aquí para que coincida con el método correcto en TokenService
    private String generateJwtToken(Authentication usuarioAutenticado) {
        return tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());  // Llamada correcta
    }
}
