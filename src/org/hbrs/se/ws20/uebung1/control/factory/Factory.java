package org.hbrs.se.ws20.uebung1.control.factory;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;

/**
 * Factory zur Erzeugung von Translator Objekten
 * Lösung: Anwendung von Factory Method Pattern
 * Problem: Inkonsitente erzeugung von Objekten
 */
public class Factory {



    public static GermanTranslator createGermanTranslator() {
        GermanTranslator germanTranslator = new GermanTranslator();
        germanTranslator.setDate("11/2020");
        return germanTranslator;
    }

}
