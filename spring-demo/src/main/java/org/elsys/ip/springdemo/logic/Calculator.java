package org.elsys.ip.springdemo.logic;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    public int plus(List<Integer> a) {
        return a.stream().reduce((x, y) -> x+y).orElse(0);
    }
}
