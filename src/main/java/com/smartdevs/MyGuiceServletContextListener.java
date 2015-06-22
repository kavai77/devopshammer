package com.smartdevs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.interceptor.InputLengthInterceptor;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class MyGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new MyServletModule());
    }

    private static class MyServletModule extends ServletModule {
        @Override
        protected void configureServlets() {
            ResourceConfig rc = new PackagesResourceConfig("com.smartdevs");
            for (Class<?> resource : rc.getClasses()) {
                bind(resource);
            }
            serve("/*").with(GuiceContainer.class);

            bindInterceptor(Matchers.any(),
                    Matchers.annotatedWith(MaxInputLengthValidator.class),
                    new InputLengthInterceptor());
        }
    }
}
