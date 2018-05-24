package com.radek.travelplanet.service;

import com.radek.travelplanet.service.parser.HtmlParser;
import com.radek.travelplanet.service.parser.ParserFactory;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class HtmlParserTest3PT {

    private static final String URL = "https://www.travelplanet.pl/wczasy/hiszpania/teneryfa/callao-salvaje/blue-sea-callao-garden--ex-vime,24052018VITX33279.html?czas=7:7&wyzywienie=1&osoby=2&lotnisko=Warszawa&dojazd=F";

    @Test
    public void shouldParseIdTagFromGivenUrl() throws Exception {
        HtmlParser htmlParser = new ParserFactory().createHtmlParser(URL);
        String price = htmlParser.parseIdTag("gnc--ttip--toggle");

        assertThat(price, Is.is("1 539 zł"));
    }
}