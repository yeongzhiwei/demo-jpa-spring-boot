package com.yeongzhiwei.demojpa.dto;

import javax.validation.constraints.NotEmpty;

import com.yeongzhiwei.demojpa.domain.Email;

import lombok.Data;

@Data
public class EmailRequest {
    
    @NotEmpty
    @javax.validation.constraints.Email
    private String address;

    public static Email toDomain(EmailRequest request) {
        Email email = new Email();
        email.setAddress(request.getAddress());
        return email;
    }

}
