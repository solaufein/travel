package com.radek.travelplanet.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {

    private String url;
    private String name;
    private String frequency;

}
