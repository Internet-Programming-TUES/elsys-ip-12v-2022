package org.elsys.ip.springdemo.web;

import org.elsys.ip.springdemo.logic.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculatorServlet extends HttpServlet {
    @Autowired
    private Calculator calculator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        List<Integer> params = req
                .getParameterMap().values().stream() //Stream<String[]>
                .flatMap(x -> Arrays.stream(x)) //Stream<String>
                .map(x -> {
                    try {
                        return Integer.parseInt(x);
                    } catch (NumberFormatException ex) {
                        return null;
                    }
                }).filter(x -> x != null).collect(Collectors.toList());

        if (pathInfo.equals("/add")) {
            int result = calculator.add(params);
            resp.getWriter().println(result);
        } else if (pathInfo.equals("/sub")) {
            int result = calculator.sub(params);
            resp.getWriter().println(result);
        } else if (pathInfo.equals("/mul")) {
            int result = calculator.mul(params);
            resp.getWriter().println(result);
        } else if (pathInfo.equals("/div")) {
            try {
                int result = calculator.div(params);
                resp.getWriter().println(result);
            } catch (Calculator.DivisionByZeroException e) {
                resp.getWriter().println("Division by zero is not possible.");
            }
        } else {
            resp.setStatus(404);
        }
    }
}
