package com.yeongzhiwei.demojpa.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.dto.PersonRequest;
import com.yeongzhiwei.demojpa.dto.PersonResponse;
import com.yeongzhiwei.demojpa.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PersonResponse> getAll() {
        return personService.findAll()
            .stream()
            .map(PersonResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PersonResponse get(@PathVariable Long id) {
        Person person = personService.find(id);
        return PersonResponse.fromDomain(person);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public PersonResponse create(@RequestBody @Valid final PersonRequest request) {
        Person person = personService.create(PersonRequest.toDomain(request));
        return PersonResponse.fromDomain(person);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public PersonResponse update(
            @PathVariable Long id,
            @RequestBody @Valid final PersonRequest request) {
        Person person = personService.update(id, PersonRequest.toDomain(request));
        return PersonResponse.fromDomain(person);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        personService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

} 
