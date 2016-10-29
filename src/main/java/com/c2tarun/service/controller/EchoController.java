package com.c2tarun.service.controller;

import com.c2tarun.service.model.EchoModelInterface;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by tarun on 10/29/16.
 */
@Path("/echo")
@Produces("text/plain")
public class EchoController {

    private final EchoModelInterface echoModel;

    @Inject
    public EchoController(EchoModelInterface echoModel) {
        this.echoModel = echoModel;
    }

    @GET
    @Produces("text/plain")
    public String sayEcho() {
        return echoModel.get();
    }
}
