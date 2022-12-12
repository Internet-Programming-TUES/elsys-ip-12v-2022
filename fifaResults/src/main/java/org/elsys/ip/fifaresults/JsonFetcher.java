package org.elsys.ip.fifaresults;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonFetcher {
    public String getMatchResultInJson() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://fixturedownload.com/feed/json/fifa-world-cup-2022")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
