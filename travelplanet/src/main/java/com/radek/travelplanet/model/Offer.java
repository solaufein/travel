package com.radek.travelplanet.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Offer implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false, unique = true)
    private String link;

    @NotNull
    @Column(nullable = false)
    private String frequency;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OfferStatus offerStatus;

    @ManyToOne
    @JoinColumn
    @NotNull
    private UserAccount userAccount;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private Set<OfferDetail> offerDetails = new HashSet<>();

    @Column
    private String failMessage;

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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        return failMessage;
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
