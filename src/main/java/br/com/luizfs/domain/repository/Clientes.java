package br.com.luizfs.domain.repository;

import br.com.luizfs.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente,Integer> {

    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    boolean existsByNome(String nome);

    @Query(value = "delete from Cliente c where c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
    Cliente findClienteFetchPedido(@Param("id") Integer id);


}
