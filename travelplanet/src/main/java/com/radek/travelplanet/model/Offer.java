package com.radek.travelplanet.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Set;

public class Offer implements Serializable {

    @Id
    private Long id;

    private String name;

    private String link;

    private String frequency;

    private OfferStatus offerStatus;

    private Set<OfferDetail> offerDetails;

    public Offer() {
        offerStatus = OfferStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Set<OfferDetail> getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(Set<OfferDetail> offerDetails) {
        this.offerDetails = offerDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        return id != null ? id.equals(offer.id) : offer.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
