package org.elsys.ip.springdemo.logic;

import java.util.List;

public class Calculator {
    public int add(List<Integer> a) {
        return a.stream().reduce((x, y) -> x+y).orElse(0);
    }

    public int sub(List<Integer> a) {
        return a.stream().reduce((x, y) -> x-y).orElse(0);
    }

    public int mul(List<Integer> a) {
        return a.stream().reduce((x, y) -> x*y).orElse(1);
    }

    public int div(List<Integer> a) throws DivisionByZeroException {
        if (a.stream().skip(1).anyMatch(x -> x == 0)) {
            throw new DivisionByZeroException();
        }

        return a.stream().reduce((x, y) -> x/y).orElse(1);
    }

    public class DivisionByZeroException extends Exception {
    }
}