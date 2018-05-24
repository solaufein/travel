package com.radek.travelplanet.service;

import com.radek.travelplanet.util.DomainUtil;

public interface OfferSite {

    String getPrice(String url);

    String getDomain();

    default boolean matches(String siteUrl) {
        String domainName = DomainUtil.getDomainName(siteUrl);
        return this.getDomain().equals(domainName);
    }
}
