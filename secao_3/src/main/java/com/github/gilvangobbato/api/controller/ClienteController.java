package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.domain.entity.Cliente;
import com.github.gilvangobbato.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository repository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        Optional<Cliente> entity = repository.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> entities = repository.findAll();
        if (entities != null && !entities.isEmpty()) {
            return ResponseEntity.ok(entities);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Cliente entity) {
        entity = repository.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Cliente> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.delete(entity.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PutMapping(value = "")
    public ResponseEntity update(@RequestBody Cliente entity) {
        return repository.findById(entity.getId()).map(value -> {
            repository.save(entity);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
