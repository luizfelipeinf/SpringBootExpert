package br.com.luizfs;

import br.com.luizfs.domain.entity.Cliente.Cliente;
import br.com.luizfs.domain.entity.Cliente.Pedido;
import br.com.luizfs.repository.Clientes;
import br.com.luizfs.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente luiz = new Cliente("Luiz");

            clientes.save(luiz);

            Pedido p = new Pedido();
            p.setCliente(luiz);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            pedidos.findByCliente(luiz).forEach(System.out::println);

            Cliente cliente = clientes.findClienteFetchPedido(luiz.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());


            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            boolean existe = clientes.existsByNome("Luis");
            System.out.println("existe Luis? " + existe);

            List<Cliente> result = clientes.encontrarPorNome("Luiz");
            result.forEach(System.out::println);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
