package io.github.gilvangobbato.services;

import io.github.gilvangobbato.model.Cliente;
import io.github.gilvangobbato.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository repository;

    public void salvarCliente(Cliente cliente){
        validateCliente(cliente);
        repository.persistence(cliente);
    }

    public void validateCliente(Cliente cliente){

    }
}
