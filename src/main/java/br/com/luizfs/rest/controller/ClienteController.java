package br.com.luizfs.rest.controller;

import br.com.luizfs.domain.entity.Cliente;
import br.com.luizfs.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;



@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clientes
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save (@RequestBody Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){
        clientes.findById(id)
                .map( cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")
                );
    }

    @PutMapping("{id}")
    @ResponseBody
    public void update (@PathVariable Integer id, @RequestBody Cliente cliente){
        clientes.findById(id)
                .map(clienteExistente -> {
                        cliente.setId(clienteExistente.getId());
                        clientes.save(cliente);
                return ResponseEntity.noContent().build();
             }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")
        );
    }

    @GetMapping
    public List<Cliente> find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }
}
