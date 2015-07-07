package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import com.smartdevs.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SamlDecoderWrongXMLTest {
    private SamlDecoder underTest;
    private String parameter;

    @Parameterized.Parameters
    public static Collection<String> data() {
        return Arrays.asList(
                "",
                "    ",
                "text",
                "<xml/>",
                "s6nIzQEA",
                "PHhtbA==");
    }

    public SamlDecoderWrongXMLTest(String parameter) {
        this.parameter = parameter;
        underTest = new SamlDecoder();
    }

    @Test(expected = BadRequestException.class)
    public void testWrongFormattedSamlRequestPost() throws Exception {
        underTest.decodePostFormat(parameter);
    }

    @Test(expected = BadRequestException.class)
    public void testWrongFormattedSamlRequestRedirect() throws Exception {
        underTest.decodeRedirectFormat(parameter);
    }
}
