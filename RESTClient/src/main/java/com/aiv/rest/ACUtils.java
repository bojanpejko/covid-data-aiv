package com.aiv.rest;

import org.apache.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ACUtils {

    public static void printResponse(HttpResponse response) throws Exception {
        System.out.print("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result);
    }
}
