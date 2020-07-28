package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entity.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PedidoItemRepository  extends JpaRepository<PedidoItem, Long> {
}
