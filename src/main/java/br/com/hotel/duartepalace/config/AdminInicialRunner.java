package br.com.hotel.duartepalace.config;

import br.com.hotel.duartepalace.model.Usuario;
import br.com.hotel.duartepalace.model.enums.RoleUsuario;
import br.com.hotel.duartepalace.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInicialRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInicialRunner(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void run(String... args) {
        if(usuarioRepository.findByUsername("admin").isEmpty()){
            Usuario usuario = new Usuario();
            usuario.setUsername("admin");
            usuario.setPassword(passwordEncoder.encode("157157"));
            usuario.setRole(RoleUsuario.ROLE_ADMIN);

            usuarioRepository.save(usuario);
        }

    }
}
