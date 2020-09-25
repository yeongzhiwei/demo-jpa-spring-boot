package com.yeongzhiwei.demojpa.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeongzhiwei.demojpa.domain.Email;
import com.yeongzhiwei.demojpa.domain.Person;

import org.springframework.util.ObjectUtils;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PersonResponse {

    private Long id;
    private String name;
    private Long spouseId;
    private Set<Long> emailIds;

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
        if (!ObjectUtils.isEmpty(person.getEmails())) {
            response.setEmailIds(person.getEmails()
                                              .stream()
                                              .map(Email::getId)
                                              .collect(Collectors.toSet()));
        }
        
        return response;
    }

}
