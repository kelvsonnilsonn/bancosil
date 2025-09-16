package com.github.bancosil;

import com.github.bancosil.application.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancosilApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancosilApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(ApplicationRunner runner){
        return args -> {
            runner.start();
        };
    }

}
