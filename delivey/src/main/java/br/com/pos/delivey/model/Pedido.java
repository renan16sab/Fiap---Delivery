package br.com.pos.delivey.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date tempoParaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido StatusPedido;

    Integer quantidade;

    @Column(name = "statusPagamento")
    Boolean pagamentoRealizado;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    Produto produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;

}
