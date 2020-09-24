package com.yeongzhiwei.demojpa.service;

import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.exception.PersonNotFoundException;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    
    public Person find(Long id) {
        return repository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Long id, Person person) {
        Person savedPerson = this.find(id);
        savedPerson.setName(person.getName());
        return repository.save(savedPerson);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            // do nothing
        }
    }

}
