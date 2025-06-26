package com.armtech.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkNotFoundException extends RuntimeException {
    public ParkNotFoundException(String id) {
        super("Could not find park with id " + id);
    }
}
