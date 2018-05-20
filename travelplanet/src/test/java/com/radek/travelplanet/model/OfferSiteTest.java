package com.radek.travelplanet.model;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.service.OfferSite;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfferSiteTest {

    private static final String TRAVELPLANET_URL = "http://www.travelplanet.pl/blabla/bla";
    private static final String UNKNOWN_URL = "http://www.onet.pl/blabla/bla";

    @Test
    public void shouldReturnInfoIfSiteMatchesGivenUrl() throws Exception {
        OfferSite offerSite = OfferSite.TRAVELPLANET;

        assertTrue(offerSite.matches(TRAVELPLANET_URL));
        assertFalse(offerSite.matches(UNKNOWN_URL));
    }

    @Test
    public void shouldReturnOfferSiteForUrl() throws Exception {
        OfferSite offerSite = OfferSite.offerSiteFor(TRAVELPLANET_URL);

        assertThat(offerSite, Is.is(OfferSite.TRAVELPLANET));
    }

    @Test(expected = OfferException.class)
    public void shouldThrowExceptionForUnknownOfferSite() throws Exception {
        OfferSite.offerSiteFor(UNKNOWN_URL);
    }
}