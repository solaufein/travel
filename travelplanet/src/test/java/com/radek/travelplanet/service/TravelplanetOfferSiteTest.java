package com.radek.travelplanet.service;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TravelplanetOfferSiteTest {
    private static final String TRAVELPLANET_URL = "http://www.travelplanet.pl/blabla/bla";
    private static final String UNKNOWN_URL = "http://www.onet.pl/blabla/bla";

    @Test
    public void shouldReturnInfoIfSiteMatchesGivenUrl() {
        TravelplanetOfferSite offerSite = new TravelplanetOfferSite();

        assertTrue(offerSite.matches(TRAVELPLANET_URL));
        assertFalse(offerSite.matches(UNKNOWN_URL));
    }

}