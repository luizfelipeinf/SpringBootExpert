package br.com.luizfs.repository;

import br.com.luizfs.domain.entity.Cliente.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
