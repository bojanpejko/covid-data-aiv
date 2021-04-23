package com.aiv.rest.data;

import com.aiv.rest.ACUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class GetDataClient extends ACUtils {

    private static final String url = "http://localhost:8080/rest/data";

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        printResponse(response);
    }
}
