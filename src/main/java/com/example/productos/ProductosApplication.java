package com.example.productos;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ProductoRepository repository) {
        return args -> {
            repository.save(new Producto("Teclado Mecánico", 15000.0, "Periféricos"));
            repository.save(new Producto("Mouse Gamer", 8000.0, "Periféricos"));
            repository.save(new Producto("Monitor 24 pulgadas", 45000.0, "Monitores"));
        };
    }
}
