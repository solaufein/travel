package com.radek.travelplanet.service;

import com.radek.travelplanet.service.parser.HtmlParser;
import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.util.NumberUtil;

public class ItakaOfferSite implements OfferSite {

    private static final String DOMAIN = "itaka.pl";
    private static final String PRICE_CLASS_TAG = "price price-box_price";
    private final ParserFactory parserFactory;

    public ItakaOfferSite(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public String getDomain() {
        return DOMAIN;
    }

    @Override
    public String getPrice(String url) {
        HtmlParser htmlParser = parserFactory.createHtmlParser(url);
        String idTagPrice = htmlParser.parseClassTag(PRICE_CLASS_TAG);
        return NumberUtil.findTextNumber(idTagPrice);
    }
}
