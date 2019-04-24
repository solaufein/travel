package com.radek.travelplanet.service.strategy;

import com.radek.travelplanet.service.parser.HtmlParser;
import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.util.NumberUtil;

public class WakacjePriceStrategy implements PriceStrategy {

    private static final String DOMAIN = "wakacje.pl";
    private static final String PRICE_ID_TAG = "descShortPrice";
    private final ParserFactory parserFactory;

    public WakacjePriceStrategy(ParserFactory parserFactory) {
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
