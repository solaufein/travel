package com.radek.travelplanet.util;

import com.radek.travelplanet.exception.OfferException;

import java.net.URI;
import java.net.URISyntaxException;

public final class DomainUtil {

    private DomainUtil() {
    }

    public static String getDomainName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException ex) {
            throw new OfferException("Cannot create URI from: " + url, ex);
        }
    }
}
