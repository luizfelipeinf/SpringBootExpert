package br.com.luizfs.service;

import br.com.luizfs.model.Cliente;
import br.com.luizfs.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository repository;

    public ClientesService( ClientesRepository repository){
        this.repository = repository;
    }

//    @Autowired
//    public void setRepository(ClientesRepository repository){
//        this.repository = repository;
//    }

    public void salvarCliene(Cliente cliente){
        validarCliene(cliente);
        this.repository.persistir(cliente);
    }
    public void validarCliene(Cliente cliente){
        //aplica validacoes
    }
}
