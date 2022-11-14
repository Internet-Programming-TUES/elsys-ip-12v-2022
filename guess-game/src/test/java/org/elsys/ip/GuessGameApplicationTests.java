package org.elsys.ip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

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

        ResponseEntity<String> getEntity = restTemplate.getForEntity(url + "?guess=" + (guessGameServlet.secretNumber+1), String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(480);

        getEntity = restTemplate.getForEntity(url + "?guess=" + (guessGameServlet.secretNumber-1), String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(490);

        getEntity = restTemplate.getForEntity(url + "?guess=" + guessGameServlet.secretNumber, String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(200);
    }

}
