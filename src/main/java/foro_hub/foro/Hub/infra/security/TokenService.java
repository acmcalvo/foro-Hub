package foro_hub.foro.Hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${JWT_SECRET}")
    private String apiSecret;

    private static final String ISSUER = "foro hub"; // El issuer se puede configurar en un campo constante.

    // Genera un token JWT para un usuario
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion()) // Expiración en 2 horas
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
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
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando la firma del token
            verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);

            // Verificar si el token ha expirado
            Instant expirationTime = verifier.getExpiresAt().toInstant();
            if (expirationTime.isBefore(Instant.now())) {
                throw new JWTVerificationException("El token ha expirado");
            }

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido", exception);
        }

        if (verifier.getSubject() == null) {
            throw new RuntimeException("No se pudo obtener el subject del token");
        }
        return verifier.getSubject();
    }

    // Genera la fecha de expiración del token (2 horas a partir de ahora)
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-08:00"));
    }
}
