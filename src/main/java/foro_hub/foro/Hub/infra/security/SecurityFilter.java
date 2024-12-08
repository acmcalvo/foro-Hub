package foro_hub.foro.Hub.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token); // extraer el nombre de usuario
            if (nombreUsuario != null) {
                // Token válido
                Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(nombreUsuario); // Método correcto

                // Verificar si el usuario existe en la base de datos
                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get(); // Extraer el usuario del Optional

                    // Crear el token de autenticación
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication); // Establecer la autenticación
                }
            }
        }
        filterChain.doFilter(request, response); // Continuar con el filtro
    }
}
