package fr.adriencaubel.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class HelloWorldController {
    @GET
    public Response helloworld(@PathParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello world");
        
        return Response.ok(response).build();
    }
}