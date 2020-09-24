package com.yeongzhiwei.demojpa.service;

import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
    
}
