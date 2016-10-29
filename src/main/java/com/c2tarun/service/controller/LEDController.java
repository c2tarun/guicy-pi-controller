package com.c2tarun.service.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Created by tarun on 10/29/16.
 */
@Singleton
@Path("/led")
public class LEDController {

    private GpioPinDigitalOutput redLed;
    private GpioPinDigitalOutput yellowLed;

    @Inject
    public LEDController(@Named("redLed") GpioPinDigitalOutput redLed,
                         @Named("yellowLed") GpioPinDigitalOutput yellowLed) {
        System.out.println("** Initializing LED Controller **");
        this.redLed = redLed;
        this.yellowLed = yellowLed;
    }

    @Path("{ledColor}/{ledAction}")
    public String controlLed(@PathParam("ledColor") String ledColor, @PathParam("ledAction") String ledAction) {
        System.out.println(String.format("Changing %s led status to %s", ledColor, ledAction));
        if("red".equalsIgnoreCase(ledColor)) {
            return performAction(redLed, ledAction);
        } else {
            // Defaulting to Yellow LED
            return performAction(yellowLed, ledAction);
        }
    }

    private String performAction(GpioPinDigitalOutput pin, String action) {
        if("on".equalsIgnoreCase(action)) {
            pin.setState(PinState.HIGH);
            return "turned ON";
        } else {
            // Defaulting to switch off LED
            pin.setState(PinState.LOW);
            return "turned OFF";
        }
    }
}
