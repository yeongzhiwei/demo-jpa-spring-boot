package com.yeongzhiwei.demojpa.dto;

import javax.validation.constraints.NotEmpty;

import com.yeongzhiwei.demojpa.domain.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonRequest {

    @NotEmpty
    private String name;

    private Long spouseId;
    
    public static Person toDomain(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        return person;
    }
    
}
