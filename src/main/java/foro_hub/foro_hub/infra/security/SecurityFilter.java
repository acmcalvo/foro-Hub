package foro_hub.foro_hub.infra.security;

import foro_hub.foro_hub.domain.model.usuario.Usuario;
import foro_hub.foro_hub.domain.model.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extraer el token del header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extraer token eliminando el prefijo "Bearer "

            try {
                procesarAutenticacion(token);
            } catch (Exception e) {
                // Configurar respuesta 401 en caso de error de autenticación
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Error de autenticación: " + e.getMessage());
                return;
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    private void procesarAutenticacion(String token) {
        // Validar y extraer el nombre de usuario del token
        Usuario usuario = tokenService.validarTokenYObtenerUsuario(token);

        // Crear y establecer la autenticación en el contexto de seguridad
        var authentication = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
