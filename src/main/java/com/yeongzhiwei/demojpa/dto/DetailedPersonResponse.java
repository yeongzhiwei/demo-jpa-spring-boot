package com.yeongzhiwei.demojpa.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeongzhiwei.demojpa.domain.Person;

import org.springframework.util.ObjectUtils;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DetailedPersonResponse {
    
    private Long id;
    private String name;
    private PersonResponse spouse;
    private Set<EmailResponse> emails;
    private Set<AddressResponse> addresses;

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
        if (!ObjectUtils.isEmpty(person.getEmails())) {
            response.setEmails(person.getEmails().stream().map(EmailResponse::fromDomain).collect(Collectors.toSet()));
        }
        if (!ObjectUtils.isEmpty(person.getAddresses())) {
            response.setAddresses(person.getAddresses().stream().map(AddressResponse::fromDomain).collect(Collectors.toSet()));
        }

        return response;
    }

}
