package org.elsys.ip;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Component
public class GuessGameServlet extends HttpServlet {
    public long secretNumber = 0;
    Random random = new Random();

    @Value("${min.number}")
    long minNumber;

    @Value("${max.number}")
    long maxNumber;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minString = req.getParameter("min");
        long min = this.minNumber;
        if (minString != null) {
            min = Long.parseLong(minString);
        }

        String maxString = req.getParameter("max");
        long max = this.maxNumber;
        if (minString != null) {
            max = Long.parseLong(maxString);
        }

        secretNumber = min + (Math.abs(random.nextLong()) % (max-min+1));
        System.out.println("New game with number: " + secretNumber + " from range(" + min + " : " + max + ")");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long guess = Long.parseLong(req.getParameter("guess"));

        if (guess == secretNumber) {
            resp.setStatus(200);
        } else if (guess > secretNumber) {
            resp.setStatus(480);
        } else {
            resp.setStatus(490);
        }
    }
}
