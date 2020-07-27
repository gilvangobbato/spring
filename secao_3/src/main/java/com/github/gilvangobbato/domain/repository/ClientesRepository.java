package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

}
