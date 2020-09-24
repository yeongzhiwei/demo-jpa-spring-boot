package com.yeongzhiwei.demojpa.dto;

import com.yeongzhiwei.demojpa.domain.Person;

import lombok.Data;

@Data
public class PersonResponse {

    private Long id;
    private String name;

    public static PersonResponse fromDomain(Person person) {
        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setName(person.getName());
        return response;
    }

}
