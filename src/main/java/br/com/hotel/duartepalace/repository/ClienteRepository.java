package br.com.hotel.duartepalace.repository;

import br.com.hotel.duartepalace.model.Cliente;
import br.com.hotel.duartepalace.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
}
