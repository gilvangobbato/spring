package com.github.gilvangobbato.domain.entity;

import com.github.gilvangobbato.common.PedidoStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    private Cliente cliente;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column(name = "PRECO", precision = 20, scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;
    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PedidoItem> itens;

}
