package com.yeongzhiwei.demojpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeongzhiwei.demojpa.domain.Person;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PersonResponse {

    private Long id;
    private String name;
    @JsonProperty("spouse_id")
    private Long spouseId;

    public static PersonResponse fromDomain(Person person) {
        PersonResponse response = new PersonResponse();

        if (person == null) {
            return response;
        }

        response.setId(person.getId());
        response.setName(person.getName());
        if (person.getSpouse() != null) {
            response.setSpouseId(person.getSpouse().getId());
        }
        return response;
    }

}
