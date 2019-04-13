package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

    Page<Offer> findAllByUserAccountEmail(@Param("email") String email, Pageable pageable);
}
