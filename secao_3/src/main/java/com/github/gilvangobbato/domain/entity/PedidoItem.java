package com.github.gilvangobbato.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO_ITEM")
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "ID")
    private Pedido pedido;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "PRODUTO_ID", referencedColumnName = "ID")
    private Produto produto;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
