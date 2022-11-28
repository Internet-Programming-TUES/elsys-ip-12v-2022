package org.elsys.ip.rest2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import java.net.http.HttpResponse;

@RestController
public class WhatIsTheTime {

    @GetMapping(value = "current-time")
    public Time currentTime(@RequestHeader(defaultValue = "false") boolean fail) throws FailException {
        if (fail) {
            throw new FailException("This is an intended fail");
        }

        long millis = System.currentTimeMillis();
        int seconds = (int) ((millis / 1000) % 60);
        int minutes = (int) ((millis / 60000) % 60);
        int hours = (int) ((millis / 3600000) % 24);
        return new Time(hours, minutes, seconds);
    }

    @PostMapping("check")
    public boolean check(@RequestBody Time time) {
        long millis = System.currentTimeMillis();
        int seconds = (int) ((millis / 1000) % 60);
        int minutes = (int) ((millis / 60000) % 60);
        int hours = (int) ((millis / 3600000) % 24);
        Time currentTime = new Time(hours, minutes, seconds);

        return Math.abs(currentTime.getHour() - time.getHour()) <= 1;
    }

    @ExceptionHandler(FailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleFail(FailException fail) {
        return new ErrorModel(fail.getMessage());
    }
}
