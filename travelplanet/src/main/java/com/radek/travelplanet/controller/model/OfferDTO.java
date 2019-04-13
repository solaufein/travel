package com.radek.travelplanet.controller.model;

import com.radek.travelplanet.model.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

    private Long id;
    private String name;
    private String link;
    private String frequency;
    private OfferStatus offerStatus;
    private String failMessage;

}
