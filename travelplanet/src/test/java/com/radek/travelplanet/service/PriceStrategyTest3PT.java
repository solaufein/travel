package com.radek.travelplanet.service;

import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.service.strategy.ItakaPriceStrategy;
import com.radek.travelplanet.service.strategy.TravelplanetPriceStrategy;
import com.radek.travelplanet.service.strategy.WakacjePriceStrategy;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class PriceStrategyTest3PT {

    private static final String URL_TRAVEL = "https://www.travelplanet.pl/wczasy/hiszpania/teneryfa/callao-salvaje/blue-sea-callao-garden--ex-vime,24052018VITX33279.html?czas=7:7&wyzywienie=1&osoby=2&lotnisko=Warszawa&dojazd=F";
    private static final String URL_ITAKA = "https://www.itaka.pl/wczasy/tunezja/djerba/hotel-zita-beach-resort-zarzis,DJEZITA.html?ofr_id=56e99ed144905eeef41c568573095cb932defbd00c7f56290a3201b108bd4fe3";
    private static final String URL_WAKACJE = "https://www.wakacje.pl/oferty/grecja/zakynthos/kalamaki/caretta-beach-472858.html?od-2019-06-19,do-2019-05-17,7-dni,samolotem,all-inclusive,z-warszawy-chopin";

    @Test
    public void shouldGetPriceFromGivenUrl_Travel() throws Exception {
        TravelplanetPriceStrategy strategy = new TravelplanetPriceStrategy(new ParserFactory());
        String price = strategy.getPrice(URL_TRAVEL);

        assertThat(price, Is.is("2039"));
    }

    @Test
    public void shouldGetPriceFromGivenUrl_Itaka() throws Exception {
        ItakaPriceStrategy strategy = new ItakaPriceStrategy(new ParserFactory());
        String price = strategy.getPrice(URL_ITAKA);

        assertThat(price, Is.is("1649"));
    }

    @Test
    public void shouldGetPriceFromGivenUrl_Wakacje() throws Exception {
        WakacjePriceStrategy strategy = new WakacjePriceStrategy(new ParserFactory());
        String price = strategy.getPrice(URL_WAKACJE);

        assertThat(price, Is.is("1790"));
    }
}