package com.arquiteturahexagonal;

import com.arquiteturahexagonal.infraestrutura.adaptadores.repositories.SpringProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SpringProdutoRepository.class)
public class ArquiteturaHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquiteturaHexagonalApplication.class, args);
    }

}
