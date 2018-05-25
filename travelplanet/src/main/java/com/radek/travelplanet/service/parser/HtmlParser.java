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
        Element element = document.getElementById(idTag);

        return getValue(idTag, element);
    }

    public String parseClassTag(String classTag) {
        Element element = document.getElementsByClass(classTag).first();

        return getValue(classTag, element);
    }

    private String getValue(String classTag, Element element) {
        if (element == null) {
            throw new OfferException("Not found element for tag: " + classTag);
        }

        return element.text();
    }
}
