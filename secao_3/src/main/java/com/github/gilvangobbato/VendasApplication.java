package com.github.gilvangobbato;

import com.github.gilvangobbato.domain.entity.Cliente;
import com.github.gilvangobbato.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository repository){
        return args -> {
            System.out.println("Salvando clientes");
            repository.save(new Cliente("Gilvan"));
            repository.save(new Cliente("Patricia"));

            List<Cliente> list = repository.findAll();
            list.forEach(System.out::println);

            System.out.println("Atualizando");
            list.forEach(c -> {
                c.setNome(c.getNome().concat(" atual"));
                repository.save(c);
            });

            System.out.println("Buscando os clientes by like");
            list = repository.searchByQuery("Gil%");
            list.forEach(System.out::println);

            System.out.println("Buscando os clientes by native query");
            list = repository.searchByQueryNative("Gil%");
            list.forEach(System.out::println);

            System.out.println("Deletando");
            list.forEach(repository::delete);

            System.out.println("Verifica deletados");
            list = repository.findAll();
            list.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }

}
