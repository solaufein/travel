package com.radek.travelplanet.util;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class DomainUtilTest {

    @Test
    public void shouldGetDomainName() {
        String domainName = DomainUtil.getDomainName("https://www.travelplanet.pl/blabla/bla");

        assertThat(domainName, Is.is("travelplanet.pl"));
    }

    @Test
    public void shouldReturnEmptyDomainForInvalidUrl() {
        String domainName = DomainUtil.getDomainName("www.onet.pl");

        assertThat(domainName, Is.is(""));

    }
}