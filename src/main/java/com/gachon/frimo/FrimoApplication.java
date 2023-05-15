package com.gachon.frimo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class FrimoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FrimoApplication.class);

        app.setDefaultProperties(Collections

            .singletonMap("server.port", "8080"));

        app.run(args);

    }


}
