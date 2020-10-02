package com.yeongzhiwei.demojpa.service;

import java.util.List;
import java.util.Set;

import com.yeongzhiwei.demojpa.domain.Email;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.exception.EmailNotFoundException;
import com.yeongzhiwei.demojpa.exception.PersonNotFoundException;
import com.yeongzhiwei.demojpa.exception.SpouseNotFoundException;
import com.yeongzhiwei.demojpa.repository.EmailRepository;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private EmailRepository emailRepository;

    public PersonService(@Autowired PersonRepository personRepository, @Autowired EmailRepository emailRepository) {
        this.personRepository = personRepository;
        this.emailRepository = emailRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }
    
    public Person findPerson(Long personId) {
        return personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
    }

    public Person createPerson(Person person, Long spouseId) {
        if (ObjectUtils.isEmpty(spouseId)) {
            return personRepository.save(person);
        }
        return setSpouse(person, spouseId);
    }

    public Person updatePerson(Long personId, Person person, Long spouseId) {
        Person savedPerson = this.findPerson(personId);
        savedPerson.setName(person.getName());

        if (ObjectUtils.isEmpty(spouseId)) {
            return personRepository.save(savedPerson);
        }
        return setSpouse(savedPerson, spouseId);
    }

    public Person setSpouse(Person person, Long spouseId) {
        Person spouse;
        try {
            spouse = findPerson(spouseId);
        } catch (PersonNotFoundException ex) {
            throw new SpouseNotFoundException();
        }

        Person oldSpouse = person.getSpouse();
        Person spouseOldSpouse = spouse.getSpouse();

        person.setSpouse(spouse);
        person = personRepository.save(person);

        spouse.setSpouse(person);
        personRepository.save(spouse);

        if (oldSpouse != null) {
            oldSpouse.setSpouse(null);
            personRepository.save(oldSpouse);
        }

        if (spouseOldSpouse != null) {
            spouseOldSpouse.setSpouse(null);
            personRepository.save(spouseOldSpouse);
        }

        return person;
    }

    public Person updatePerson(Long personId, Person person) {
        Person savedPerson = this.findPerson(personId);
        savedPerson.setName(person.getName());
        return personRepository.save(savedPerson);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    public void deletePerson(Long personId) {
        try {
            personRepository.deleteById(personId);
        } catch (EmptyResultDataAccessException ex) {
            // do nothing
        }
    }

    public Set<Email> findAllEmails(Long personId) {
        Person person = findPerson(personId);
        return person.getEmails();
    }
    
    public Email findEmail(Long personId, Long emailId) {
        Email email = emailRepository.findById(emailId).orElseThrow(EmailNotFoundException::new);
        if (email.getOwner() == null || !email.getOwner().getId().equals(personId)) {
            throw new EmailNotFoundException();
        }
        return email;
    }

    public Email addEmail(Long personId, Email email) {
        Person person = findPerson(personId);
        email.setOwner(person);
        return emailRepository.save(email);
    }

    public Email updateEmail(Long personId, Long emailId, Email email) {
        Email savedEmail = findEmail(personId, emailId);
        savedEmail.setAddress(email.getAddress());
        return emailRepository.save(savedEmail);
    }

    public void deleteAllEmail(Long personId) {
        Person person = findPerson(personId);
        Set<Email> emails = person.getEmails();
        emailRepository.deleteAll(emails);
    }

    public void deleteEmail(Long personId, Long emailId) {
        try {
            Email email = findEmail(personId, emailId);
            emailRepository.delete(email);
        } catch (EmptyResultDataAccessException | EmailNotFoundException ex) {
            // do nothing
        }
    }

}
