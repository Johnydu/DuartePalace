package br.com.hotel.duartepalace.service;

import br.com.hotel.duartepalace.exeption.BusinessException;
import br.com.hotel.duartepalace.model.Quarto;
import br.com.hotel.duartepalace.model.StatusQuarto;
import br.com.hotel.duartepalace.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public Quarto createQuarto(Quarto quarto){
        try{
            return quartoRepository.save(quarto);
        }catch (BusinessException e){
            throw new BusinessException("Erro ao criar quarto");
        }
    }

    public List<Quarto> findbyQuartosDisponiveis() {

        return quartoRepository.findAll().stream()
                .filter(q -> q.getStatusQuarto()==StatusQuarto.DISPONIVEL)
                .toList();
    }

    public Optional<Quarto> findByNumeroQuarto(Integer numeroQuarto){
       try{
           return quartoRepository.findByNumeroQuarto(numeroQuarto);
       }catch (BusinessException e){
           throw new BusinessException("Quarto não encontrado");
       }
    }

    public Quarto alterarStatusQuarto(Integer numeroQuarto, StatusQuarto statusQuarto){
        if (statusQuarto == null) {
            throw new BusinessException("Status do quarto é obrigatório");
        }
        Quarto quarto = quartoRepository.findByNumeroQuarto(numeroQuarto)
                .orElseThrow(()-> new BusinessException("Quarto não encontrado"));
        quarto.setStatusQuarto(statusQuarto);
        return  quartoRepository.save(quarto);
    }


}


