package com.yeongzhiwei.demojpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeongzhiwei.demojpa.domain.Address;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AddressResponse {
    
    private Long id;
    private Integer postalCode;

    public static AddressResponse fromDomain(Address address) {
        AddressResponse response = new AddressResponse();

        if (address == null) {
            return response;
        }

        response.setId(address.getId());
        response.setPostalCode(address.getPostalCode());
        
        return response;
    }

}
