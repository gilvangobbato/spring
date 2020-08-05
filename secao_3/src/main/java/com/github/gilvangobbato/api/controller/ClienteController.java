package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.domain.entity.Cliente;
import com.github.gilvangobbato.domain.repository.ClientesRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Api("Api Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository repository;

    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 400, message = "Cliente não encontrado"),
    })
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") @ApiParam("Id do cliente") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));


    }

    @GetMapping(value = "", produces = {"application/json"})
    public List<Cliente> getAll() {
        return repository.findAll();
    }
    @ApiOperation("Salva um cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erros de validação"),
    })
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente entity) {
        return repository.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        repository.findById(id).map(value -> {
            repository.delete(value);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PutMapping(value = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Cliente entity) {
        repository.findById(entity.getId()).map(value ->
                repository.save(entity))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @GetMapping("/find")
    public List<Cliente> find(Cliente entity) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(entity, matcher);
        return repository.findAll(example);

    }
}
