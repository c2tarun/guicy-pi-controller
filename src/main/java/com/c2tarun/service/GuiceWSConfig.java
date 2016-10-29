package com.c2tarun.service;

import com.c2tarun.service.controller.EchoController;
import com.c2tarun.service.controller.LEDController;
import com.c2tarun.service.controller.RelayController;
import com.c2tarun.service.model.EchoModel;
import com.c2tarun.service.model.EchoModelInterface;
import com.c2tarun.service.modules.PinConfigurationModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarun on 10/29/16.
 */
public class GuiceWSConfig extends GuiceServletContextListener {
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(EchoController.class);
                bind(LEDController.class);
                bind(RelayController.class);

                bind(EchoModelInterface.class).to(EchoModel.class);

                Map<String, String> initParams = new HashMap<String, String>();
                initParams.put("com.sun.jersey.config.feature.Trace", "true");
                serve("/*").with(GuiceContainer.class, initParams);
            }
        }, new PinConfigurationModule());
    }
}
