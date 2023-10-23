package com.example.mainservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api/v1")
public class HelloApplication extends Application {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("resteasy.media.type", "application/json");
        return properties;
    }
}