package br.com.hotel.duartepalace.repository;

import br.com.hotel.duartepalace.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto,Long> {
    Optional<Quarto> findByNumeroQuarto(Integer numeroQuarto);

}
