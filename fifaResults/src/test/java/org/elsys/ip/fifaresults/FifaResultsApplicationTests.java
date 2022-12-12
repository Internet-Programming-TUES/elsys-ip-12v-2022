package org.elsys.ip.fifaresults;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@SpringBootTest
class FifaResultsApplicationTests {

    @Autowired
    private JsonFetcher jsonFetcher;

    @Autowired
    private ResultsLoader resultsLoader;

    @Test
    void jsonFetcher() throws IOException {
        System.out.println(
                jsonFetcher.getMatchResultInJson());
    }

    @Test
    void resultsLoader() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        System.out.println(
                resultsLoader.getResults().size());
    }

}
