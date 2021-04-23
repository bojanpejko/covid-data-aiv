package com.aiv.rest.data;

import com.aiv.rest.ACUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;

public class PostDataClient extends ACUtils {

    private static final String url = "http://localhost:8080/rest/data";
    private static final String path = "src/main/resources/json-test-inputs/data-post-request.json";

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        File json = new File(path);
        FileEntity entity = new FileEntity(json);

        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(request);

        printResponse(response);
    }

}
