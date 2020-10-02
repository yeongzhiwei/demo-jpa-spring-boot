package com.yeongzhiwei.demojpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find email")
public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4854716390554975796L;
    
}
