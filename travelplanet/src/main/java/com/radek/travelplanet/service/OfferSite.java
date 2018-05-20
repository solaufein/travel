package com.radek.travelplanet.service;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.util.DomainUtil;

import java.util.Arrays;

public enum OfferSite {
    TRAVELPLANET("travelplanet.pl", "gnc--ttip--toggle");

    private final String domain;
    private final String idTag;

    OfferSite(String domain, String idTag) {
        this.domain = domain;
        this.idTag = idTag;
    }

    public String getDomain() {
        return domain;
    }

    public String getIdTag() {
        return idTag;
    }

    public boolean matches(String siteUrl) {
        String domainName = DomainUtil.getDomainName(siteUrl);
        return this.getDomain().equals(domainName);
    }

    public static OfferSite offerSiteFor(String url) {
        return Arrays.stream(values())
                .filter(offerSite -> offerSite.matches(url))
                .findFirst()
                .orElseThrow(() -> new OfferException("Not found OfferSite for given url: " + url));
    }
}
