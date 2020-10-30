package br.com.efo.dbc.analisedados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AnaliseDadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnaliseDadosApplication.class, args);
    }

}
