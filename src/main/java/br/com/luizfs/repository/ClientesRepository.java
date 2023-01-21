package br.com.luizfs.repository;

import br.com.luizfs.model.Cliente;
import br.com.luizfs.service.ClientesService;
import org.springframework.stereotype.Repository;

@Repository
public class ClientesRepository {

    public void persistir(Cliente cliente) {
        // acessa base e salva o cliente
    }
}
