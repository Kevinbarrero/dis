package ru.itis.s2_lab4.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.itis.s2_lab4.orm.EntityManagerFactory;

@Configuration
@ComponentScan({"ru.itis.s2_lab4"})
public class Config {


    @Bean(destroyMethod = "destroy")
    @Scope("singleton")
    public EntityManagerFactory getEntityManagerFactory(CharSequence url) {
        return new EntityManagerFactory(url);
    }

    @Bean
    public CharSequence getUrl () {
        return "url";
    }

}