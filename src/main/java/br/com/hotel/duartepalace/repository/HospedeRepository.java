package br.com.hotel.duartepalace.repository;

import br.com.hotel.duartepalace.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Optional<Hospede> findByCpf(String cpf);
}
