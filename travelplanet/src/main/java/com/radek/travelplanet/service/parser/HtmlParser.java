package com.radek.travelplanet.service.parser;

import com.radek.travelplanet.exception.OfferException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HtmlParser {

    private final String url;

    public HtmlParser(String url) {
        this.url = url;
    }

    public String parseIdTag(String idTag) {
        Document doc = getDocument();
        Element idElement = doc.getElementById(idTag);

        if (idElement == null) {
            throw new OfferException("Not found idTag: " + idTag);
        }

        return idElement.text();
    }

    private Document getDocument() {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            throw new OfferException("Cannot connect to: " + url, ex);
        }
    }
}
