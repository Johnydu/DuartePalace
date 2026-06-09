package br.com.hotel.duartepalace.service;

import br.com.hotel.duartepalace.exeption.BusinessException;
import br.com.hotel.duartepalace.model.Usuario;
import br.com.hotel.duartepalace.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if(usuario.isEmpty()){
            throw  new UsernameNotFoundException("Usuario não encontrado");
        }
        return User.builder()
                .username(usuario.get().getUsername())
                .password(usuario.get().getPassword())
                .roles(usuario.get().getRole())
                .build();
    }

    public Usuario  cadastrarUsuario(Usuario usuario){
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new BusinessException("Nome de usuário já cadastrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    
}
