package io.github.gilvangobbato;

import io.github.gilvangobbato.anotations.Enviroment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Enviroment
public class MyConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de vendas";
    }

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Esta em config development");
        };
    }

}
