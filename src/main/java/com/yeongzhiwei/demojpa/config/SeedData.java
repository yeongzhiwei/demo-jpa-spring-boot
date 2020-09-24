package com.yeongzhiwei.demojpa.config;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class SeedData {
    
    @Bean
    public CommandLineRunner seedPersonRepo(PersonRepository repository) {
        return args -> {
            repository.save(new Person("Adam"));
            repository.save(new Person("Ben"));
            repository.save(new Person("Charlie"));
            repository.save(new Person("Dan"));
            repository.save(new Person("Ezekiel"));
        };
    }

}
