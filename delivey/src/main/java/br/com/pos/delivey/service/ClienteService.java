package br.com.pos.delivey.service;

import br.com.pos.delivey.model.Cliente;
import br.com.pos.delivey.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(String cpf) {
        return clienteRepository.findClienteByCpf(cpf);
    }
}
