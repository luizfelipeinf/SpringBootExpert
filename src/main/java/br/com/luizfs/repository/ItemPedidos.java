package br.com.luizfs.repository;


import br.com.luizfs.domain.entity.Cliente.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidos extends JpaRepository<ItemPedido,Integer> {
}
