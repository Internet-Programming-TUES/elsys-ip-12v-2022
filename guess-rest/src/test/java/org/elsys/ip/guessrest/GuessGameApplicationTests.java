package org.elsys.ip.guessrest;

import org.elsys.ip.guessrest.service.GuessGameService;
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
    private GuessGameService guessGameService;

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
        String gameId = null;

        do {
            ResponseEntity<String> postEntity = restTemplate.postForEntity(url, "", String.class);
            assertThat(postEntity.getStatusCodeValue()).isEqualTo(200);
            gameId = postEntity.getBody();
        } while (guessGameService.getGames().get(gameId) == minNumber || guessGameService.getGames().get(gameId) == maxNumber);

        long secretNumber = guessGameService.getGames().get(gameId);

        ResponseEntity<String> getEntity = restTemplate.getForEntity(url + "/" + gameId + "?guess=" + (secretNumber+1), String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(480);

        getEntity = restTemplate.getForEntity(url + "/" + gameId + "?guess=" + (secretNumber-1), String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(490);

        getEntity = restTemplate.getForEntity(url + "/" + gameId + "?guess=" + secretNumber, String.class);
        assertThat(getEntity.getStatusCodeValue()).isEqualTo(200);
    }

}
