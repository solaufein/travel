package com.radek.travelplanet.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OfferSiteTest {

    @Test
    public void shouldReturnInfoIfSiteMatchesGivenUrl() throws Exception {
        OfferSite offerSite = OfferSite.TRAVELPLANET;

        assertTrue(offerSite.matches("http://www.travelplanet.pl/blabla/bla"));
        assertFalse(offerSite.matches("http://www.onet.pl/blabla/bla"));
    }

}