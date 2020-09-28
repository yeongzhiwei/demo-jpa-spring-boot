package com.yeongzhiwei.demojpa.config;

import com.yeongzhiwei.demojpa.domain.Address;
import com.yeongzhiwei.demojpa.domain.Email;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.AddressRepository;
import com.yeongzhiwei.demojpa.repository.EmailRepository;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@Profile("dev")
public class SeedData {
    
    @Bean
    public CommandLineRunner seedPersonRepo(
            TransactionTemplate transactionTemplate, 
            PersonRepository personRepository, 
            EmailRepository emailRepository,
            AddressRepository addressRepository) {
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

            Email email1 = new Email();
            email1.setAddress("hello@world.com");
            email1.setOwner(person1);

            Email email2 = new Email();
            email2.setAddress("good@bye.com");
            email2.setOwner(person1);

            Address address1 = new Address();
            address1.setPostalCode(111111);
            address1.addOccupier(person1);
            address1.addOccupier(person2);

            Address address2 = new Address();
            address2.setPostalCode(222222);
            address2.addOccupier(person2);
            address2.addOccupier(person3);

            transactionTemplate.execute(new TransactionCallbackWithoutResult(){
                @Override
                public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    personRepository.save(person1);
                    personRepository.save(person2);
                    personRepository.save(person3);
                    personRepository.save(person4);
                    emailRepository.save(email1);
                    emailRepository.save(email2);
                    addressRepository.save(address1);
                    addressRepository.save(address2);
                }
            });
        };
    }

}
