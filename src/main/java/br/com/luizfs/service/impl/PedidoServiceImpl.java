package br.com.luizfs.service.impl;

import br.com.luizfs.domain.entity.Cliente;
import br.com.luizfs.domain.entity.ItemPedido;
import br.com.luizfs.domain.entity.Pedido;
import br.com.luizfs.domain.entity.Produto;
import br.com.luizfs.domain.enums.StatusPedido;
import br.com.luizfs.domain.repository.Clientes;
import br.com.luizfs.domain.repository.ItemsPedido;
import br.com.luizfs.domain.repository.Pedidos;
import br.com.luizfs.domain.repository.Produtos;
import br.com.luizfs.exception.PedidoNaoEncontradoException;
import br.com.luizfs.exception.RegraNegocioException;
import br.com.luizfs.rest.dto.ItemPedidoDTO;
import br.com.luizfs.rest.dto.PedidoDTO;
import br.com.luizfs.service.PedidoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;
    private Clientes clientesRepository;
    private Produtos produtosRepository;
    private ItemsPedido itemsPedidoRepository;

    public PedidoServiceImpl(Pedidos repository, Clientes clientesRepository, Produtos produtosRepository, ItemsPedido itemsPedidoRepository) {
        this.repository = repository;
        this.clientesRepository = clientesRepository;
        this.produtosRepository = produtosRepository;
        this.itemsPedidoRepository = itemsPedidoRepository;
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();

        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }
                ).orElseThrow( () -> new PedidoNaoEncontradoException());
    }

    public List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Nao e possivel realizar um pedido sem itens");
        }

        return items
                .stream()
                .map( dto -> {

                    Integer idProduto = dto.getProduto();

                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow( () ->
                                    new RegraNegocioException("Codigo de produto invalido"+ idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
