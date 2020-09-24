package com.yeongzhiwei.demojpa.dto;

import javax.validation.constraints.NotEmpty;

import com.yeongzhiwei.demojpa.domain.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    @NotEmpty
    private String name;
    
    public static Person toDomain(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        return person;
    }
    
}
