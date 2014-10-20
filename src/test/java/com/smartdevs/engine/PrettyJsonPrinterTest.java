package com.smartdevs.engine;

import com.smartdevs.exception.PrettyJsonCreationException;
import org.junit.Test;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonPrinterTest {
    @Test(expected = PrettyJsonCreationException.class)
    public void failsWhenJsonInputIsNull() throws Exception {
        PrettyJsonPrinter printer = new PrettyJsonPrinter(null);
        printer.getPrettyJson();
    }

    @Test(expected = PrettyJsonCreationException.class)
    public void failsWhenJsonIsMissingTheClosingTag() throws Exception {
        PrettyJsonPrinter printer = new PrettyJsonPrinter("{");
        printer.getPrettyJson();
    }

    @Test(expected = PrettyJsonCreationException.class)
    public void failsWhenJsonContentIsInvalid() throws Exception {
        PrettyJsonPrinter printer = new PrettyJsonPrinter("{\"}");
        printer.getPrettyJson();
    }
}
