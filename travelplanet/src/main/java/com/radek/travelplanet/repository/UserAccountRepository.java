package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
    UserAccount findByEmail(@Param("email") String email);
}
