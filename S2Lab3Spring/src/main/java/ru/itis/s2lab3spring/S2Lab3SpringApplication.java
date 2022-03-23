package ru.itis.s2lab3spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class S2Lab3SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(S2Lab3SpringApplication.class, args);
    }

}
