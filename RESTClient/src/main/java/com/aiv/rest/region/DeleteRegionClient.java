package com.aiv.rest.region;

import com.aiv.rest.ACUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;

public class DeleteRegionClient extends ACUtils {

    //Zmenjamo UUID z podatek iz našo bazo
    private static final String url = "http://localhost:8080/rest/region/{UUID}";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        HttpResponse response = client.execute(request);

        printResponse(response);
    }

}
