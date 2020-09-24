package com.yeongzhiwei.demojpa.controller;

import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@ResponseBody
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Person> getAll() {
        return personService.findAll();
    }

}
