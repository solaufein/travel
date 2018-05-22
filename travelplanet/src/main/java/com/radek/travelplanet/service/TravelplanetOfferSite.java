package com.radek.travelplanet.service;

public class TravelplanetOfferSite implements OfferSite {

    private static final String DOMAIN = "travelplanet.pl";
    private static final String PRICE_ID_TAG = "gnc--ttip--toggle";

    //todo: consider here getPrice method: 'String parse(String url)' that will be using here HtmlParser

    @Override
    public String getDomain() {
        return DOMAIN;
    }

    @Override
    public String getIdTag() {
        return PRICE_ID_TAG;
    }
}
