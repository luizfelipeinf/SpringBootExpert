package br.com.luizfs.repository;

import br.com.luizfs.domain.entity.Cliente.Cliente;
import br.com.luizfs.domain.entity.Cliente.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido,Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
