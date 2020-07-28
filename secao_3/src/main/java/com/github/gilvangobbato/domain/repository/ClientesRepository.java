package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * é igual a consulta findByNameLike mas utilizando uma consulta
     * personalizada com HQL
     *
     * @param nome
     * @return
     */
    @Query(value = "select c from Cliente c where c.nome like :nome ")
    List<Cliente> searchByQuery(@Param("nome") String nome);

    /**
     * é igual a consulta findByNameLike mas utilizando uma consulta
     * personalizada UTILIZANDO NATIVE QUERY
     *
     * @param nome
     * @return
     */
    @Query(value = "SELECT c.* FROM cliente c WHERE c.nome LIKE (:nome) ", nativeQuery = true)
    List<Cliente> searchByQueryNative(@Param("nome") String nome);

    @Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos p WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Long id);
}
