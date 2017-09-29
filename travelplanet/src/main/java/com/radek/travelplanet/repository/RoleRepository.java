package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends CrudRepository<Role, Long> {
}
