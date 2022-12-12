package org.elsys.ip.fifaresults;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResultsLoader {
    @Autowired
    private JsonFetcher jsonFetcher;

    public List<MatchResult> getResults() throws IOException {
        String matchResultInJson = jsonFetcher.getMatchResultInJson();

        ObjectMapper mapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MatchResult[] matchResults = mapper.readValue(matchResultInJson, MatchResult[].class);

        return Arrays.asList(matchResults);
    }
}
