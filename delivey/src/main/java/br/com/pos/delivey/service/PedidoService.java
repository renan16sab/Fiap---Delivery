package br.com.pos.delivey.service;

import br.com.pos.delivey.model.Pedido;
import br.com.pos.delivey.model.Produto;
import br.com.pos.delivey.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodosOsPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarPedidosPeloUsuario(Long usuarioId) {
        return pedidoRepository.findPedidoByCliente_Id(usuarioId);
    }
}
