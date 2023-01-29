package br.com.luizfs.service;

import br.com.luizfs.domain.entity.Pedido;
import br.com.luizfs.domain.enums.StatusPedido;
import br.com.luizfs.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
