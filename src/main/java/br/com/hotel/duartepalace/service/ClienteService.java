package br.com.hotel.duartepalace.service;

import br.com.hotel.duartepalace.exeption.BusinessException;
import br.com.hotel.duartepalace.model.Cliente;
import br.com.hotel.duartepalace.repository.ClienteRepository;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;

    }

    public Cliente cadastrarCliente(Cliente cliente){
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorCPF(String cpf){
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(()-> new BusinessException("cliente não encontrado"));
    }

    public Cliente atulizarCadastroCliente(Cliente clienteAtualizado){
        clienteRepository.findById(clienteAtualizado .getId()).orElseThrow(()-> new BusinessException("Cliente não encontrado"));

        clienteAtualizado .setNome(clienteAtualizado.getNome());
        clienteAtualizado .setCpf(clienteAtualizado.getCpf());
        clienteAtualizado .setTelefone(clienteAtualizado.getTelefone());
        clienteAtualizado .setUsuarioPremium(clienteAtualizado.getUsuarioPremium());

        return clienteRepository.save(clienteAtualizado);

    }
}




