package org.elsys.ip.fifaresults;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
        System.out.println(
                resultsLoader.getResults().size());
    }

}
