package org.elsys.ip;

import org.assertj.core.internal.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuessGameApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GuessGameServlet guessGameServlet;

    private String url;

    @Value("${min.number}")
    int minNumber;

    @Value("${max.number}")
    int maxNumber;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/game";
    }

    @Test
    void guess() {
        do {
            ResponseEntity<String> postEntity = restTemplate.postForEntity(url, "", String.class);
            assertThat(postEntity.getStatusCodeValue()).isEqualTo(200);
        } while (guessGameServlet.secretNumber == minNumber || guessGameServlet.secretNumber == maxNumber);

        int minRange = minNumber;
        int maxRange = maxNumber;
        int guesses = 0;

        System.out.println("The number is " + guessGameServlet.secretNumber);

        do {
            guesses += 1;
            int guess = (minRange + maxRange) / 2;

            System.out.println("Try with "+ guess);
            ResponseEntity<String> getEntity =
                    restTemplate.getForEntity(url + "?guess=" + guess, String.class);
            if (getEntity.getStatusCodeValue() == 200) {
                break;
            }
            if (getEntity.getStatusCodeValue() == 480) {
                maxRange = guess-1;
            }
            if (getEntity.getStatusCodeValue() == 490) {
                minRange = guess+1;
            }
        } while (guesses < 1000000);

        System.out.println("It took " + guesses + " guesses");
    }

}
