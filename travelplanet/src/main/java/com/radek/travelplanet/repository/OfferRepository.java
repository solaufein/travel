package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OfferRepository extends MongoRepository<Offer, Long> {

    List<Offer> findByName(@Param("name") String name);

}
