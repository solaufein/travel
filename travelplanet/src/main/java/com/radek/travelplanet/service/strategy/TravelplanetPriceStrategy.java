package com.radek.travelplanet.service.strategy;

import com.radek.travelplanet.service.parser.HtmlParser;
import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.util.NumberUtil;

public class TravelplanetPriceStrategy implements PriceStrategy {

    private static final String DOMAIN = "travelplanet.pl";
    private static final String PRICE_ID_TAG = "gnc--ttip--toggle";
    private final ParserFactory parserFactory;

    public TravelplanetPriceStrategy(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public String getDomain() {
        return DOMAIN;
    }

    @Override
    public String getPrice(String url) {
        HtmlParser htmlParser = parserFactory.createHtmlParser(url);
        String idTagPrice = htmlParser.parseIdTag(PRICE_ID_TAG);
        return NumberUtil.findTextNumber(idTagPrice);
    }
}
