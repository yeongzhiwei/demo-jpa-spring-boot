package com.yeongzhiwei.demojpa.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yeongzhiwei.demojpa.domain.Email;
import com.yeongzhiwei.demojpa.dto.EmailRequest;
import com.yeongzhiwei.demojpa.dto.EmailResponse;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons/{personId}/emails")
public class EmailController {
    
    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<?> getAll(@PathVariable Long personId) {
        return personService.findAllEmails(personId).stream().map(EmailResponse::fromDomain).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long personId, @PathVariable Long id) {
        Email email = personService.findEmail(personId, id);
        return ResponseEntity.status(HttpStatus.OK).body(EmailResponse.fromDomain(email));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmailResponse create(@PathVariable Long personId, @RequestBody @Valid final EmailRequest request) {
        Email email = personService.addEmail(personId, EmailRequest.toDomain(request));
        return EmailResponse.fromDomain(email);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public EmailResponse update(
            @PathVariable Long personId,
            @PathVariable Long id,
            @RequestBody @Valid final EmailRequest request) {
        Email email = personService.updateEmail(personId, id, EmailRequest.toDomain(request));
        return EmailResponse.fromDomain(email);
    }


    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable Long personId) {
        personService.deleteAllEmail(personId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long personId, Long id) {
        personService.deleteEmail(personId, id);
    }

}
