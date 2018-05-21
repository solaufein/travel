package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.OfferSite;
import com.radek.travelplanet.service.parser.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfferTaskCommand implements TaskCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferTaskCommand.class);
    private final Offer offer;

    public OfferTaskCommand(Offer offer) {
        this.offer = offer;
    }

    @Override
    public void execute() {
        String link = offer.getLink();
        OfferSite offerSite = OfferSite.offerSiteFor(link);
        HtmlParser htmlParser = new HtmlParser(link);
        String idText = htmlParser.parseIdTag(offerSite.getIdTag());

        LOGGER.info("Offer name: {}, link: {}. price: {}.", offer.getName(), link, idText);
    }
}
