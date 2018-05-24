package com.radek.travelplanet.service.parser;

import com.radek.travelplanet.exception.OfferException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public final class HtmlParser {

    private final Document document;

    HtmlParser(Document document) {
        this.document = document;
    }

    public String parseIdTag(String idTag) {
        Element idElement = document.getElementById(idTag);

        if (idElement == null) {
            throw new OfferException("Not found idTag: " + idTag);
        }

        return idElement.text();
    }

}
