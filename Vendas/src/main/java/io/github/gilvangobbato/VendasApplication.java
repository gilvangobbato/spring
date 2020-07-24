package io.github.gilvangobbato;

import io.github.gilvangobbato.anotations.Animal;
import io.github.gilvangobbato.anotations.Cachorro;
import io.github.gilvangobbato.anotations.Gato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

//    @Autowired
//    @Qualifier("applicationName")

    //customização de anotações
    @Gato
    private Animal gato;
    @Cachorro
    private Animal can;

    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String hello(){
        return applicationName;
    }

    @Bean(name = "exucutarAnimal")
    public CommandLineRunner runner(){
        return args -> {
            this.gato.fazerBarulho();
            this.can.fazerBarulho();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
