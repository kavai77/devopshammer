package com.smartdevs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/warmup")
public class WarmUpService {
    @GET
    public Response warmUp() {
        return Response.ok().build();
    }
}
