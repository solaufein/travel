package com.radek.travelplanet.repository;

import com.radek.travelplanet.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    Employee findByCoreId(@Param("coreId") String coreId);
}
