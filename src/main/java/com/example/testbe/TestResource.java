package com.example.testbe;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import json.JSONUtil;
import org.json.simple.JSONObject;

@Path("/test")
public class TestResource {
    JSONUtil jsonUtil = new JSONUtil();

    @GET
    @Produces("application/json")
    public JSONObject hello() {
        return this.jsonUtil.trasformaJson();
    }
}