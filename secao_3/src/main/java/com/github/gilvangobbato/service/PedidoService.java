package com.github.gilvangobbato.service;

import com.github.gilvangobbato.common.PedidoStatus;
import com.github.gilvangobbato.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService {
    Pedido save(Pedido peido);

    Optional<Pedido> findById(Long id);

    void updateStatus(PedidoStatus status, Long id);
}
