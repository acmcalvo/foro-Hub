package foro_hub.foro.Hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${API_SECURITY_SECRET}")
    private String apiSecret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Verifica si el token es válido
    public boolean esTokenValido(String token) {
        try {
            getSubject(token);  // Si esto no lanza una excepción, el token es válido
            return true;
        } catch (RuntimeException e) {
            return false; // Si el token no es válido, se captura la excepción y se retorna false
        }
    }

    // Genera un token JWT para un usuario
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(usuario.getLogin())  // El login del usuario
                    .withClaim("id", usuario.getId()) // El ID del usuario como un claim adicional
                    .withExpiresAt(generarFechaExpiracion()) // Fecha de expiración del token
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    // Obtiene el 'subject' (usuario) del token
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token no puede ser nulo");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando la firma
            verifier = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido", exception);
        }

        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("Token inválido o verificador no válido");
        }
        return verifier.getSubject();
    }

    // Método para obtener el usuario usando el token (el 'subject' del token)
    public Usuario obtenerUsuario(String token) {
        String username = getSubject(token);
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Genera una fecha de expiración para el token (2 horas a partir de ahora)
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-08:00"));
    }
}
