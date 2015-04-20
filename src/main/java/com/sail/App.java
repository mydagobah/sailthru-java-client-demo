package com.sail;

import java.io.IOException;
import java.util.*;
import com.sailthru.client.SailthruClient;
import com.sailthru.client.exceptions.ApiException;
import com.sailthru.client.handler.response.JsonResponse;
import com.sailthru.client.params.Content;

public class App {
    String apiKey = "replace-with-your-api-key";
    String apiSec = "replace-with-your-api-sec";
    SailthruClient client = new SailthruClient(apiKey, apiSec);

    public static void main( String[] args ) {
        App demo = new App();
        demo.testContent();
    }

    // test email GET
    public void testGetEmail() {
	try {
            JsonResponse response = client.getEmail("wzhang@sailthru.com");
            printResponse(response);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // test content POST
    public void testContent() {
        Content content = new Content();
        content = content.setUrl("http://www.sailthru.com/content/test/002")
                         .setThumbImage("http://i2.cdn.turner.com/money/dam/assets/140909142343-apple-watch-620xa.jpg")
                         .setTitle("Apple Watch")
                         .setDescription("Apple Watch")
                         .setLocation(45.735, -22.4908)
                         .setAuthor("Steve Jobs")
                         .setPrice(12800000);
        try {
            JsonResponse response = client.pushContent(content);
            printResponse(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printResponse(JsonResponse response) {
    	Map<String, Object> res = response.getResponse();

        for(String k : res.keySet()) {
        	System.out.println(k + " => " + res.get(k));
        }
    }
}
