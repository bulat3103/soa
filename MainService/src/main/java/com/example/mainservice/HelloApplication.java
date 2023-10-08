package com.example.mainservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class HelloApplication extends Application {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}