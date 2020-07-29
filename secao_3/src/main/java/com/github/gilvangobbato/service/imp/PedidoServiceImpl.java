package com.github.gilvangobbato.service.imp;

import com.github.gilvangobbato.domain.entity.Pedido;
import com.github.gilvangobbato.domain.repository.ClientesRepository;
import com.github.gilvangobbato.domain.repository.PedidoRepository;
import com.github.gilvangobbato.domain.repository.ProdutoRepository;
import com.github.gilvangobbato.exception.RegraException;
import com.github.gilvangobbato.service.PedidoService;
import com.github.gilvangobbato.common.PedidoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClientesRepository cliRepo;
    @Autowired
    private ProdutoRepository produtoRepository;

//  criar um contrutor que recebe o service por parametro seria a mesma coisa que injetar
//  utilizando o @Autowired
//
//    public PedidoServiceImpl(PedidoRepository repository) {this.repository = repository;}

    @Override
    @Transactional
    public Pedido save(Pedido entity) {
        cliRepo.findById(entity.getCliente().getId())
                .map(value -> {
                    entity.setCliente(value);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new RegraException("Cliente não encontrado"));

        if (entity.getItens() == null && entity.getItens().isEmpty()) {
            throw new RuntimeException("Pedido sem itens");
        }
        entity.getItens().forEach(value -> {
            produtoRepository.findById(value.getProduto().getId())
                    .map(it -> {
                        value.setProduto(it);
                        return Void.TYPE;
                    })
                    .orElseThrow(() -> new RegraException("Produto não encontrado."));
            value.setPedido(entity);
        });
        entity.setStatus(PedidoStatus.REALIZADO);
        return repository.save(entity);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void updateStatus(PedidoStatus status, Long id) {
        Pedido entity = repository.findById(id)
                .map(value-> {
                    value.setStatus(status);
                    return value;
                })
                .orElseThrow(() -> new RegraException("Pedido não encontrado."));
        repository.save(entity);
    }
}
