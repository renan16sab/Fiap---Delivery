package br.com.pos.delivey.repository;

import br.com.pos.delivey.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findClienteByCpf(String cpf);
}
