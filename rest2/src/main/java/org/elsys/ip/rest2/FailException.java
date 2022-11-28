package org.elsys.ip.rest2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No such Order")
public class FailException extends Exception {
    public FailException(String message) {
        super(message);
    }
}
