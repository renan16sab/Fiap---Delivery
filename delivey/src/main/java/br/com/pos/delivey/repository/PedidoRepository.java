package br.com.pos.delivey.repository;

import br.com.pos.delivey.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface PedidoRepository extends JpaRepository<Pedido,Long> {
        List<Pedido> findPedidoByCliente_Id(Long id);
    }

