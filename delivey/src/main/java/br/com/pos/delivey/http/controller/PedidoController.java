package br.com.pos.delivey.http.controller;


import br.com.pos.delivey.model.Pedido;
import br.com.pos.delivey.model.Produto;
import br.com.pos.delivey.model.StatusPedido;
import br.com.pos.delivey.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumSet;
import java.util.List;


@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ModelMapper modelMapper;


    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(description = "Insere o pedido")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salva(@RequestBody Pedido pedido) {
        return pedidoService.salvarPedido(pedido);
    }

    @Operation(description = "Busca o pedido pela id do usuário")
    @GetMapping("/pedido/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> buscarProdutoPorCategoria(@PathVariable("usuarioId") Long usuarioId) {
        return pedidoService.listarPedidosPeloUsuario(usuarioId);
    }

    @Operation(description = "Atualiza o status de pagamento do pedido e o status do pedido")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPedido(@PathVariable("id") Long id,@RequestBody Pedido pedido) {
        pedidoService.buscarPorId(id)
                .map(pendBase ->  {
                    modelMapper.map(pedido, pendBase);
                    System.out.println(pedidoService.buscarPorId(id));
                    System.out.println("Atualizando pedido " + pedido);
                    System.out.println("Atualizando pedido2 " + pendBase);

                    pedidoService.salvarPedido(pendBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));

    }

    @Operation(description = "Busca todos os pedidos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> buscarProdutoPorCategoria() {
        return pedidoService.listarTodosOsPedidos();
    }
}
