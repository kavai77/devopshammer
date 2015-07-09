package com.smartdevs;

import com.smartdevs.engine.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PrettyJsonPrinter.class).to(PrettyJsonPrinter.class);
        bind(PrettyXmlPrinter.class).to(PrettyXmlPrinter.class);
        bind(SamlDecoder.class).to(SamlDecoder.class);
        bind(SamlEncoder.class).to(SamlEncoder.class);
        bind(X509CertificateDecoder.class).to(X509CertificateDecoder.class);
    }
}