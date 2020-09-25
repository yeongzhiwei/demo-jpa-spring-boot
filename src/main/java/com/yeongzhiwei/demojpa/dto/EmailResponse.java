package com.yeongzhiwei.demojpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeongzhiwei.demojpa.domain.Email;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmailResponse {
    
    private Long id;
    private String address;

    public static EmailResponse fromDomain(Email email) {
        EmailResponse response = new EmailResponse();

        if (email == null) {
            return response;
        }

        response.setId(email.getId());
        response.setAddress(email.getAddress());
        
        return response;
    }

}
