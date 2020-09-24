package com.yeongzhiwei.demojpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find person")
public class PersonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 243063078559082913L;
    
}
