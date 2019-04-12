package com.radek.travelplanet.service.strategy;

import com.radek.travelplanet.util.DomainUtil;

public interface PriceStrategy {

    String getPrice(String url);

    String getDomain();

    default boolean matches(String url) {
        String domainName = DomainUtil.getDomainName(url);
        return this.getDomain().equals(domainName);
    }
}
