package com.radek.travelplanet.service;

import com.radek.travelplanet.service.parser.ParserFactory;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class OfferSiteTest3PT {

    private static final String URL_TRAVEL = "https://www.travelplanet.pl/wczasy/hiszpania/teneryfa/callao-salvaje/blue-sea-callao-garden--ex-vime,24052018VITX33279.html?czas=7:7&wyzywienie=1&osoby=2&lotnisko=Warszawa&dojazd=F";
    private static final String URL_ITAKA = "https://www.itaka.pl/wczasy/tunezja/djerba/hotel-meninx-djerba,DJEMENI.html?ofr_id=937f3d2698cc55c95b1423bd5627ffd7d720b63a103dc445e2d2a2393b88984f";

    @Test
    public void shouldGetPriceFromGivenUrl_Travel() throws Exception {
        TravelplanetOfferSite travelplanetOfferSite = new TravelplanetOfferSite(new ParserFactory());
        String price = travelplanetOfferSite.getPrice(URL_TRAVEL);

        assertThat(price, Is.is("1899"));
    }

    @Test
    public void shouldGetPriceFromGivenUrl_Itaka() throws Exception {
        ItakaOfferSite itakaOfferSite = new ItakaOfferSite(new ParserFactory());
        String price = itakaOfferSite.getPrice(URL_ITAKA);

        assertThat(price, Is.is("1199"));
    }
}