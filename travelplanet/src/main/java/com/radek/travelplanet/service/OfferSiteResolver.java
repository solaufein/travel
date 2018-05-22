package com.radek.travelplanet.service;

import com.radek.travelplanet.exception.OfferException;

import java.util.List;

public class OfferSiteResolver {

    private final List<OfferSite> offerSites;

    public OfferSiteResolver(List<OfferSite> offerSites) {
        this.offerSites = offerSites;
    }

    public OfferSite resolveOfferSite(String url) {
        return offerSites.stream()
                .filter(offerSite -> offerSite.matches(url))
                .findFirst()
                .orElseThrow(() -> new OfferException("Not found OfferSite for given url: " + url));
    }
}
