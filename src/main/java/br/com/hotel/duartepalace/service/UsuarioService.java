package br.com.hotel.duartepalace.service;

import br.com.hotel.duartepalace.dto.UsuarioDTO;
import br.com.hotel.duartepalace.dto.UsuarioEdicaoDTO;
import br.com.hotel.duartepalace.dto.UsuarioListaDTO;
import br.com.hotel.duartepalace.exception.BusinessException;
import br.com.hotel.duartepalace.exception.ResourceNotFoundException;
import br.com.hotel.duartepalace.mapper.UsuarioMapper;
import br.com.hotel.duartepalace.model.Usuario;
import br.com.hotel.duartepalace.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public Usuario novoUsuario(@NonNull @Valid  UsuarioDTO usuarioDto) {
        if(usuarioRepository.findByUsername(usuarioDto.username()).isPresent()){
            throw new  BusinessException("Usuario já cadastrado");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuario.setPassword(passwordEncoder.encode(usuarioDto.password()));

        return usuarioRepository.save(usuario);

    }

    public Usuario editarUsuario(UsuarioEdicaoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuario.setUsername(dto.getUsername());
        usuario.setRole(dto.getRole());

        if (dto.getNovaSenha() != null && !dto.getNovaSenha().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getNovaSenha()));
        }

        return usuarioRepository.save(usuario);
    }

   public List<UsuarioListaDTO> listaDeUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> new UsuarioListaDTO(usuario.getId()
                ,usuario.getUsername(),
                usuario.getRole()))
                .toList();
   }

    public void excluirUsuario(Long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id invalido"));
        usuarioRepository.deleteById(id);
    }
}



