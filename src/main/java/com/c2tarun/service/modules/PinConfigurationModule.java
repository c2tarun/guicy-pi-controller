package com.c2tarun.service.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.pi4j.io.gpio.*;

import javax.inject.Named;

/**
 * Created by tarun on 10/29/16.
 */

public class PinConfigurationModule extends AbstractModule {
    protected void configure() {

    }

    @Provides
    public GpioController provideGpioController() {
        return GpioFactory.getInstance();
    }

    @Singleton
    @Provides
    @Named("redLed")
    public GpioPinDigitalOutput provideRedLEDPin(GpioController gpioController) {
        System.out.println("*** Initializing RED LED Pin ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "myredled", PinState.LOW);
    }

    @Singleton
    @Provides
    @Named("yellowLed")
    public GpioPinDigitalOutput provideYelloLEDPin(GpioController gpioController) {
        System.out.println("*** Initializing Yellow LED Pin ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_27, "myyellowled", PinState.LOW);
    }

    /**
     * Due to some reason PinState.HIGH is off in relay switch.
     *
     * @param gpioController
     * @return
     */
    @Singleton
    @Provides
    @Named("IN1")
    public GpioPinDigitalOutput getIn1Pin(GpioController gpioController) {
        System.out.println("*** Initializing IN1 ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "IN1", PinState.HIGH);
    }

    @Singleton
    @Provides
    @Named("IN2")
    public GpioPinDigitalOutput getIn2Pin(GpioController gpioController) {
        System.out.println("*** Initializing IN2 ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "IN2", PinState.HIGH);
    }

    @Singleton
    @Provides
    @Named("IN3")
    public GpioPinDigitalOutput getIn3Pin(GpioController gpioController) {
        System.out.println("*** Initializing IN3 ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_16, "IN3", PinState.HIGH);
    }

    @Singleton
    @Provides
    @Named("IN4")
    public GpioPinDigitalOutput getIn4Pin(GpioController gpioController) {
        System.out.println("*** Initializing IN4 ***");
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_15, "IN4", PinState.HIGH);
    }
}
