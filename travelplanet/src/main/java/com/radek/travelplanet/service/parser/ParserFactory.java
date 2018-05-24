package com.radek.travelplanet.service.parser;

import com.radek.travelplanet.exception.OfferException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserFactory {

    public HtmlParser createHtmlParser(String url) {
        Document document = getDocument(url);
        return new HtmlParser(document);
    }

    private Document getDocument(String url) {
        try {
            Connection connection = Jsoup.connect(url);
            return connection.get();
        } catch (IOException ex) {
            throw new OfferException("Cannot connect to: " + url, ex);
        }
    }
}
