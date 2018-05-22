package com.radek.travelplanet.service;

import com.radek.travelplanet.util.DomainUtil;

public interface OfferSite {

    String getDomain();

    String getIdTag();

    default boolean matches(String siteUrl) {
        String domainName = DomainUtil.getDomainName(siteUrl);
        return this.getDomain().equals(domainName);
    }
}
