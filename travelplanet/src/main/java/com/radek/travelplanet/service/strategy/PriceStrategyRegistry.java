package com.radek.travelplanet.service.strategy;

import com.radek.travelplanet.exception.OfferException;

import java.util.List;

public class PriceStrategyRegistry {

    private final List<PriceStrategy> priceStrategies;

    public PriceStrategyRegistry(List<PriceStrategy> priceStrategies) {
        this.priceStrategies = priceStrategies;
    }

    public PriceStrategy resolveStrategy(String url) {
        return priceStrategies.stream()
                .filter(strategy -> strategy.matches(url))
                .findFirst()
                .orElseThrow(() -> new OfferException("Not found PriceStrategy for given url: " + url));
    }
}
