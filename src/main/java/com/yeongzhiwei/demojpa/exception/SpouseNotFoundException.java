package com.yeongzhiwei.demojpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find spouse")
public class SpouseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1140652019942076978L;
    
}
