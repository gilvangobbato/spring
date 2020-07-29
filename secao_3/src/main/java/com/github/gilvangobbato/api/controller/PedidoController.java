package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.common.PedidoStatus;
import com.github.gilvangobbato.domain.entity.Pedido;
import com.github.gilvangobbato.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    /**
     * Passando o service pelo contrutor o Spring realiza
     * a injeção automaticamente
     * @param service
     */
    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody Pedido pedido){
        service.save(pedido);
        return pedido.getId();
    }

    @GetMapping(value = "/{id}")
    public Pedido findById(@PathVariable("id") Long id){
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pedido não encontrado"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus( @RequestBody PedidoStatus status,
                              @PathVariable Long id){
        service.updateStatus(status, id);
    }
}
