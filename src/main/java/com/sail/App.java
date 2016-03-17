package com.sail;

import com.sailthru.client.SailthruClient;
import com.sailthru.client.exceptions.ApiException;
import com.sailthru.client.handler.response.JsonResponse;
import com.sailthru.client.params.Content;
import com.sailthru.client.params.User;

import java.io.IOException;
import java.util.Map;

public class App {
    private static final String apiKey = "replace-with-your-api-key";
    private static final String apiSec = "replace-with-your-api-key";
    private static final String apiUrl = "http://api.sailthru.com"; // specify for internal testing
    private static SailthruClient client;

    public static void main( String[] args ) {
        client = new SailthruClient(apiKey, apiSec, apiUrl);
        App demo = new App();
        demo.getUser();
    }

    // GET /user
    public void getUser() {
        User user = new User("foo@bar.com");
        user.setKey("email");
        try {
            JsonResponse response = client.getUser(user);
            System.out.println("Response:");
            System.out.println(response.toString());
        } catch (IOException e) {
            // handle exception
        }
    }


    // GET /email
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

    // POST /content
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
