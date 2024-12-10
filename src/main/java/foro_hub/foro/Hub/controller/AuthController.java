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
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenSvc;

    @PostMapping("/authenticate")
    public ResponseEntity<DatosJWTToken> authenticateUser(@RequestBody DatosAutenticacionUsuario authData) {
        Authentication authenticationToken = createAuthenticationToken(authData);

        Authentication authenticatedUser = authManager.authenticate(authenticationToken);

        Usuario loggedInUser = (Usuario) authenticatedUser.getPrincipal();

        // Cambiar aquí a generarToken
        String jwtToken = tokenSvc.generarToken(loggedInUser);  // Llamada correcta al método

        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

    private Authentication createAuthenticationToken(DatosAutenticacionUsuario authData) {
        return new UsernamePasswordAuthenticationToken(
                authData.getLogin(),
                authData.getClave()
        );
    }
}
