package br.com.hotel.duartepalace.service;

import br.com.hotel.duartepalace.exeption.BusinessException;
import br.com.hotel.duartepalace.model.Hospede;
import br.com.hotel.duartepalace.repository.HospedeRepository;
import org.springframework.stereotype.Service;


@Service
public class HospedeService {
    private final HospedeRepository hospedeRepository;

    public HospedeService(HospedeRepository hospedeRepository){
        this.hospedeRepository = hospedeRepository;

    }

    public Hospede cadastrarCliente(Hospede hospede){
        if (hospedeRepository.findByCpf(hospede.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado");
        }

        return hospedeRepository.save(hospede);
    }

    public Hospede buscarPorCPF(String cpf){
        return hospedeRepository.findByCpf(cpf)
                .orElseThrow(()-> new BusinessException("cliente não encontrado"));
    }

    public Hospede atulizarCadastroCliente(Hospede hospedeAtualizado){
        hospedeRepository.findById(hospedeAtualizado.getId()).orElseThrow(()-> new BusinessException("Cliente não encontrado"));

        hospedeAtualizado.setNome(hospedeAtualizado.getNome());
        hospedeAtualizado.setCpf(hospedeAtualizado.getCpf());
        hospedeAtualizado.setTelefone(hospedeAtualizado.getTelefone());
        hospedeAtualizado.setUsuarioPremium(hospedeAtualizado.getUsuarioPremium());

        return hospedeRepository.save(hospedeAtualizado);

    }

    public void deletarCliente(Hospede hospede){
        try{
            hospedeRepository.delete(hospede);
        }catch (BusinessException e){
            throw new BusinessException("Erro ao deletar cliente");
        }

    }
}




