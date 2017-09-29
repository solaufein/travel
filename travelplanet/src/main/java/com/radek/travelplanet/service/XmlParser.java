package com.radek.travelplanet.service;

public class XmlParser {

    //todo: ... different parsers in future for other websites (use interface -> chainOfResp)

//    private String url;
//    private Document doc;
//    private int priceInteger;
//
//    public XmlParser(String url) {
//        this.url = url;
//    }
//
//    public int actualPrice() throws IOException {
//        //connect to given web site DOM
//        doc = Jsoup.connect(url).get();
//        Element element = doc.getElementById("hidPrice");      // get element with price
//
//        if (element != null) {
//            String price = element.text();
//            priceInteger = Integer.parseInt(price);
//
//            return priceInteger;
//        } else return -1;
//    }
}
