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
    public int secretNumber = 0;
    Random random = new Random();

    @Value("${min.number}")
    int minNumber;

    @Value("${max.number}")
    int maxNumber;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        secretNumber = random.nextInt(maxNumber+1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int guess = Integer.parseInt(req.getParameter("guess"));
        if (guess < minNumber || guess > maxNumber) {
            resp.setStatus(479);
        } else if (guess == secretNumber) {
            resp.setStatus(200);
        } else if (guess > secretNumber) {
            resp.setStatus(480);
        } else {
            resp.setStatus(490);
        }
    }
}
