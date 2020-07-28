package com.github.gilvangobbato.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "PEDIDO_ITEM")
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "ID")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID", referencedColumnName = "ID")
    private Produto produto;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
