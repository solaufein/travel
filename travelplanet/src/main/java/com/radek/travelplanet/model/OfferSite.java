package com.radek.travelplanet.model;

import com.radek.travelplanet.util.DomainUtil;

public enum OfferSite {
    TRAVELPLANET("travelplanet.pl");

    private final String value;

    OfferSite(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String siteUrl) {
        String siteDomain = DomainUtil.getDomainName(siteUrl);
        return this.getValue().equals(siteDomain);
    }
}
