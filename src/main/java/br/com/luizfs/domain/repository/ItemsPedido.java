package br.com.luizfs.domain.repository;


import br.com.luizfs.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido,Integer> {
}
