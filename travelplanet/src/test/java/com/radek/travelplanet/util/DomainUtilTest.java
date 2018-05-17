package com.radek.travelplanet.util;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class DomainUtilTest {

    private static final String URL = "https://www.travelplanet.pl/blabla/bla";

    @Test
    public void shouldGetDomainName() throws Exception {
        String domainName = DomainUtil.getDomainName(URL);

        assertThat(domainName, Is.is("travelplanet.pl"));
    }

}