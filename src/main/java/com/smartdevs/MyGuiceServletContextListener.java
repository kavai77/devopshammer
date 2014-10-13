package com.smartdevs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class MyGuiceServletContextListener extends GuiceServletContextListener {

        @Override protected Injector getInjector() {
                return Guice.createInjector(
                        new MyServletModule() );
        }

        private static class MyServletModule extends ServletModule {
                @Override protected void configureServlets() {
                        serve( "/*" ).with( SamlServlet.class );
                }
        }
}
