package com.yeongzhiwei.demojpa.config;

import java.util.ArrayList;
import java.util.List;

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
            Person person1 = new Person();
            person1.setName("Adam");

            Person person2 = new Person();
            person2.setName("Ben");

            Person person3 = new Person();
            person3.setName("Cleo");

            Person person4 = new Person();
            person4.setName("Dan");

            person4.setSpouse(person3);
            person3.setSpouse(person4);

            List<Person> persons = new ArrayList<>();
            persons.add(person1);
            persons.add(person2);
            persons.add(person3);
            persons.add(person4);
            repository.saveAll(persons);
        };
    }

}
