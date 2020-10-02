package com.yeongzhiwei.demojpa.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.dto.DetailedPersonResponse;
import com.yeongzhiwei.demojpa.dto.PersonRequest;
import com.yeongzhiwei.demojpa.dto.PersonResponse;
import com.yeongzhiwei.demojpa.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<?> getAll(@RequestParam(name = "expand", required = false) String expand) {
        if (expand == null) {
            return personService.findAllPersons().stream().map(PersonResponse::fromDomain).collect(Collectors.toList());
        } else {
            return personService.findAllPersons().stream().map(DetailedPersonResponse::fromDomain).collect(Collectors.toList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id, @RequestParam(name = "expand", required = false) String expand) {
        Person person = personService.findPerson(id);
        if (expand == null) {
            return ResponseEntity.status(HttpStatus.OK).body(PersonResponse.fromDomain(person));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(DetailedPersonResponse.fromDomain(person));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public PersonResponse create(@RequestBody @Valid final PersonRequest request) {
        Person person = personService.createPerson(PersonRequest.toDomain(request));
        return PersonResponse.fromDomain(person);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public PersonResponse update(
            @PathVariable Long id,
            @RequestBody @Valid final PersonRequest request) {
        Person person = personService.updatePerson(id, PersonRequest.toDomain(request));
        return PersonResponse.fromDomain(person);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        personService.deleteAllPersons();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personService.deletePerson(id);
    }

} 
