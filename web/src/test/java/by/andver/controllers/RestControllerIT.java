package by.andver.controllers;

import by.andver.objects.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;


public class RestControllerIT {
    private static final String URI_REST_SERVICE="http://localhost:8080/rest/";

    @Test
    public void shouldPostUserFromJSON() throws IOException {
        String userName="userTEST";
        HttpUriRequest request1=new HttpGet(URI_REST_SERVICE+"users/"+userName);
        HttpResponse httpResponse1= HttpClientBuilder.create().build().execute(request1);
        Assert.assertEquals(httpResponse1.getStatusLine().getStatusCode(),HttpStatus.SC_NOT_FOUND);

        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity postString=new StringEntity("{\"username\": \""+userName+"\", \"password\": \"44566548465132\", \"name\": \"ОАО Полесьежилстрой\", \"address\": \"г. Брест, ул. Кижеватова 60\", \"telNumber\": \"+375162572257\", \"email\": \"office@pzs.by\"}");
        HttpPost post=new HttpPost(URI_REST_SERVICE+"users");
        post.setEntity(postString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response=httpClient.execute(post);

        HttpUriRequest request=new HttpGet(URI_REST_SERVICE+"users/"+userName);
        HttpResponse httpResponse= HttpClientBuilder.create().build().execute(request);
        User user= retrieveResourceFromResponse(httpResponse, User.class);
        Assert.assertEquals(user.getUsername(),userName);
    }

    @Test
    public void notFoundUser() throws IOException {
        String name= "STRANGER";
        HttpUriRequest request=new HttpGet(URI_REST_SERVICE+"users/"+name);
        HttpResponse httpResponse= HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_NOT_FOUND);
    }
    @Test
    public void shouldGetUserByName() throws IOException {
        String name= "userTEST";
        HttpUriRequest request=new HttpGet(URI_REST_SERVICE+"users/"+name);
        HttpResponse httpResponse= HttpClientBuilder.create().build().execute(request);
        User user= retrieveResourceFromResponse(httpResponse, User.class);
        Assert.assertEquals(name,user.getUsername());
    }
    @Test
    public void shouldGetJSONUser() throws IOException {
        String jsonMimeType="application/json";
        String name= "userTEST";
        HttpUriRequest request=new HttpGet(URI_REST_SERVICE+"users/"+name);
        HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
        String mimeType= ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        Assert.assertEquals(jsonMimeType,mimeType);
    }

    private static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException {
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }
}