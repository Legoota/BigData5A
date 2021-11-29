package com.example.bigdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BigdataApplication {

    @Autowired
    private ProduitInterface produitInterface;
    public static void main(String[] args) {
        SpringApplication.run(BigdataApplication.class, args);
    }

    // je veux insérer des produits dans la table produit au moment
    // de déploiement de notre API REST
    @Bean
    CommandLineRunner runner() {
        return args -> {
            produitInterface.save(new Produit(1, "tablette", 1020));
            produitInterface.save(new Produit(2, "pc", 2000));
            produitInterface.save(new Produit(3, "portable", 800));
        };
    }
}
