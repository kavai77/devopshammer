package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import com.smartdevs.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SamlEncoderWrongXMLTest {
    private SamlEncoder underTest;
    private String parameter;

    @Parameterized.Parameters
    public static Collection<String> data() {
        return Arrays.asList(
                "",
                "    ",
                "text",
                "<xml",
                "xml>",
                "<xml>",
                "<xml attr=\"/>",
                "<xml></xml1>");
    }

    public SamlEncoderWrongXMLTest(String parameter) {
        this.parameter = parameter;
        underTest = new SamlEncoder();
    }

    @Test(expected = BadRequestException.class)
    public void testWrongFormattedSamlRequestPost() throws Exception {
        underTest.encodeSamlRequest(parameter, SamlResponse.BindingFormat.POST);
    }

    @Test(expected = BadRequestException.class)
    public void testWrongFormattedSamlRequestRedirect() throws Exception {
        underTest.encodeSamlRequest(parameter, SamlResponse.BindingFormat.REDIRECT);
    }
}
