package com.smartdevs.engine;

import com.smartdevs.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonPrinterTest {
    private PrettyJsonPrinter underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new PrettyJsonPrinter();
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenJsonInputIsNull() throws Exception {
        underTest.getPrettyJson(null);
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenJsonIsMissingTheClosingTag() throws Exception {
        underTest.getPrettyJson("{");
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenJsonContentIsInvalid() throws Exception {
        underTest.getPrettyJson("{\"}");
    }
}
