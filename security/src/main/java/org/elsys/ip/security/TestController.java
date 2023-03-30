package org.elsys.ip.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }
}
