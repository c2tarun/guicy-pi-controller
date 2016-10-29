package com.c2tarun.service.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by tarun on 10/29/16.
 */
@Singleton
@Path("/relay")
public class RelayController {
    private final GpioPinDigitalOutput in1;
    private final GpioPinDigitalOutput in2;
    private final GpioPinDigitalOutput in3;
    private final GpioPinDigitalOutput in4;

    @Inject
    public RelayController(@Named("IN1") GpioPinDigitalOutput in1,
                           @Named("IN2") GpioPinDigitalOutput in2,
                           @Named("IN3") GpioPinDigitalOutput in3,
                           @Named("IN4") GpioPinDigitalOutput in4) {
        this.in1 = in1;
        this.in2 = in2;
        this.in3 = in3;
        this.in4 = in4;
    }

    // Example relayPortName 1, 2, 3 and 4
    @Path("{relayPinId}/{relayAction}")
    public String controlRelaySwitch(@PathParam("relayPinId") int relayPinId,
                                     @PathParam("relayAction") String relayAction) {
        switch (relayPinId) {
            case 1:
                performAction(in1, relayAction);
                break;
            case 2:
                performAction(in2, relayAction);
                break;
            case 3:
                performAction(in3, relayAction);
                break;
            case 4:
                performAction(in4, relayAction);
        }
        return "Action Successfull";
    }

    private void performAction(GpioPinDigitalOutput gpioPin, String action) {
        if("on".equals(action)) {
            gpioPin.setState(PinState.LOW);
        } else {
            gpioPin.setState(PinState.HIGH);
        }
    }
}
