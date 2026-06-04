package br.com.hotel.duartepalace.repository;

import br.com.hotel.duartepalace.exeption.ResourceNotFoundException;
import br.com.hotel.duartepalace.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}

