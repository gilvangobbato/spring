package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

    /**
     * Usando esta sintaxe find+By+propriedade+like ira
     * realizar um like pelo parametro
     *
     * @param nome
     * @return
     */
    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrId(String nome, Long id);

    Cliente findOneByNome(String nome);
}
