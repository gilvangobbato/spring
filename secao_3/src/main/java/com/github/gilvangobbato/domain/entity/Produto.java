package com.github.gilvangobbato.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
    @Column(name = "PRECO_UNITARIO", precision = 20, scale = 2)
    private BigDecimal preco;

}
