package org.elsys.ip.guess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class Player {
    public static void main(String[] args) {
        long min = 0;
        long max = 2000l;

        RestTemplate client = new RestTemplate();
        client.setErrorHandler(new NoOpErrorHandler());
        if (client.postForEntity("http://localhost:8080/game?min={min}&max={max}", null, String.class, min, max).getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        int count = 0;
        do {
            long guess = (min + max) / 2;
            count++;
            System.out.println(guess);
            ResponseEntity<Object> guessResult = client.getForEntity("http://localhost:8080/game?guess=" + guess, null, String.class);

            if (guessResult.getStatusCodeValue() == 200) {
                System.out.println("Count: " + count);
                break;
            }

            if (guessResult.getStatusCodeValue() == 480) {
                max = guess - 1;
            }

            if (guessResult.getStatusCodeValue() == 490) {
                min = guess + 1;
            }
        } while (true);
    }
}

class NoOpErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}