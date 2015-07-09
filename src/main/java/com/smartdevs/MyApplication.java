package com.smartdevs;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new MyApplicationBinder());
        packages(true, "com.smartdevs.service");
        register(MultiPartFeature.class);
    }
}