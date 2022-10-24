package org.elsys.ip.springdemo.logic;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Logic {
    private final Calculator calculator;

    public Logic(Calculator calculator) {
        this.calculator = calculator;
        System.out.println("Logic is created");
    }

    @PostConstruct
    public void logic() {
        //System.out.println("5 + 4 = " + calculator.aplusb(5,4));
    }
}
