package com.radek.travelplanet.util;

import com.radek.travelplanet.exception.OfferException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public final class DomainUtil {

    private DomainUtil() {
    }

    public static String getDomainName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return Optional.ofNullable(domain)
                    .map(d -> d.startsWith("www.") ? d.substring(4) : d)
                    .orElse("");
        } catch (URISyntaxException ex) {
            throw new OfferException("Cannot create URI from: " + url, ex);
        }
    }
}
