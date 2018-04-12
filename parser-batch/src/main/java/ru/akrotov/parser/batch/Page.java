package ru.akrotov.parser.batch;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Page {

    // TODO: Body or Full Page?
    public String getBody(String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
            // body = new String(body.getBytes(), "utf-8"); // TODO: Нужно?
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
