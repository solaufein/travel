package com.radek.travelplanet.service;

import com.radek.travelplanet.service.parser.HtmlParser;
import com.radek.travelplanet.service.parser.ParserFactory;

public class TravelplanetOfferSite implements OfferSite {

    private static final String DOMAIN = "travelplanet.pl";
    private static final String PRICE_ID_TAG = "gnc--ttip--toggle";
    private final ParserFactory parserFactory;

    public TravelplanetOfferSite(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public String getDomain() {
        return DOMAIN;
    }

    @Override
    public String getPrice(String url) {
        HtmlParser htmlParser = parserFactory.createHtmlParser(url);
        return htmlParser.parseIdTag(PRICE_ID_TAG);
    }
}
