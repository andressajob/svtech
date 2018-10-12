package com.victorseger.cursomc.config;

import com.victorseger.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


//classe de configuração com o profile de teste conforme indicado no application.properties
@Configuration
@Profile("test")
public class TestConfig {


    @Autowired
    private DBService dbService;


    //método para instanciar base de dados
    @Bean
    public boolean instantiateDatabase() throws ParseException {

        dbService.instantiateDataBase();
        return true;
    }

    //método para instanciar mail service mock
/*    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }*/

}
