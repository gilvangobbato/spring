package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.domain.entity.Cliente;
import com.github.gilvangobbato.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        if(entity.isPresent()){
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save/{entity}")
    public ResponseEntity<Long> save(@PathVariable("entity") Cliente entity){
        entity = repository.save(entity);
        return ResponseEntity.ok(entity.getId());
    }
}
