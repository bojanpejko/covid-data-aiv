package com.aiv.rest.region;

import com.aiv.rest.ACUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;

public class PutRegionClient extends ACUtils {

    //Zmenjamo UUID z podatek iz našo bazo
    private static final String url = "http://localhost:8080/rest/region";
    private static final String path = "src/main/resources/json-test-inputs/region-put-request.json";

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(url);

        File json = new File(path);
        FileEntity entity = new FileEntity(json);

        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(request);

        printResponse(response);
    }

}
