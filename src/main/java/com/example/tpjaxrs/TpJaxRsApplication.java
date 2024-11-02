package com.example.tpjaxrs;

import com.example.tpjaxrs.entity.Compte;
import com.example.tpjaxrs.entity.TypeCompte;
import com.example.tpjaxrs.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TpJaxRsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpJaxRsApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            compteRepository.save(new Compte(0, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(0, Math.random() * 9000, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(0, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));

            compteRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
    }
}
