package com.yeongzhiwei.demojpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeongzhiwei.demojpa.domain.Person;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DetailedPersonResponse {
    
    private Long id;
    private String name;
    private PersonResponse spouse;

    public static DetailedPersonResponse fromDomain(Person person) {
        DetailedPersonResponse response = new DetailedPersonResponse();
        
        if (person == null) {
            return response;
        }

        response.setId(person.getId());
        response.setName(person.getName());
        if (person.getSpouse() != null) {
            response.setSpouse(PersonResponse.fromDomain(person.getSpouse()));
        }
        return response;
    }

}
