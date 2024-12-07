package foro_hub.foro.Hub.config;

import foro_hub.foro.Hub.domain.usuario.Usuario;
import foro_hub.foro.Hub.domain.usuario.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepository.findByUsername(username);
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            return org.springframework.security.core.userdetails.User
                    .withUsername(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles(usuario.getRole())  // Asignar el rol del usuario
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para codificar contraseñas
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()  // Cambiado de authorizeRequests() a authorizeHttpRequests()
                .requestMatchers("/api/usuarios/registrar").permitAll()  // Permite acceso libre a /registrar
                .anyRequest().authenticated()  // Requiere autenticación para otros endpoints
                .and()
                .httpBasic();  // Autenticación básica
        return http.build();
    }
}
