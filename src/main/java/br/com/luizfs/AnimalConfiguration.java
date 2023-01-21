package br.com.luizfs;

import br.com.luizfs.service.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {
    @Bean(name = "cao")
    public Animal cao(){
        return new Animal() {
            @Override
            public void fazerBarulho(){
                System.out.println("Au au");
            }
        };
    }
    @Bean(name = "gato")
    public Animal gato(){
        return new Animal() {
            @Override
            public void fazerBarulho(){
                System.out.println("Mi au");
            }
        };
    }
}
