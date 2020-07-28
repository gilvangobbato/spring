package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entity.Cliente;
import com.github.gilvangobbato.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PedidoRepository  extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);
}
