package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.Offer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

    List<Offer> findByName(@Param("name") String name);

}
