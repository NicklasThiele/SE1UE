package org.hbrs.se.ws20.uebung1.BBTest;

import org.hbrs.se.ws20.uebung1.control.Translator;
import org.hbrs.se.ws20.uebung1.control.factory.Factory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslatorTest {
    //private Client client = null;
    private Translator translator = null;

    @BeforeEach
    void setup() {
        //client = Factory.createClient();
        translator = Factory.createGermanTranslator();

    }
    @AfterEach
    void teardown() {
        //client = null;
        translator = null;
    }
    @Test
    void testTranslateNumber() {
        //positiv
        assertEquals("eins", translator.translateNumber(1), "TranslateNumber Fehler bei 1");
        //assertEquals("zehn", translator.translateNumber(10), "TranslateNumber Fehler bei 10");
        //negativ
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)", translator.translateNumber(11), "TranslateNumber Fehler bei 11");
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0), "TranslateNumber Fehler bei 11");
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)", translator.translateNumber(-1), "TranslateNumber Fehler bei 11");

    }

}
