package com.radek.travelplanet.service;

import com.radek.travelplanet.model.*;
import com.radek.travelplanet.repository.EmployeeRepository;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbDataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbDataInitializer.class);
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;
    private final OfferRepository offerRepository;

    @Autowired
    public DbDataInitializer(RoleRepository roleRepository, EmployeeRepository employeeRepository, OfferRepository offerRepository) {
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("Populating sample data in db.");

        offerRepository.deleteAll();
        employeeRepository.deleteAll();
        roleRepository.deleteAll();

        Role adminRole = createRole(RoleType.ADMIN);
        Role userRole = createRole(RoleType.USER);
        Role savedAdminRole = roleRepository.save(adminRole);
        Role savedUserRole = roleRepository.save(userRole);

        Employee admin = new Employee();
        admin.setCoreId("admin");
        admin.setFirstName("John");
        admin.setLastName("Smith");
        admin.setState(State.ACTIVE);
        admin.setPassword("admin");
        admin.getRolesSet().add(savedAdminRole);
        employeeRepository.save(admin);

        Employee employee = new Employee();
        employee.setCoreId("user");
        employee.setFirstName("Jessica");
        employee.setLastName("White");
        employee.setState(State.ACTIVE);
        employee.setPassword("user");
        employee.getRolesSet().add(savedUserRole);
        employeeRepository.save(employee);

        Offer offer = new Offer();
        offer.setFrequency("10");
        offer.setLink("www.google.com");
        offer.setName("Spain Week");
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offerRepository.save(offer);

        LOGGER.info("Data populated in db.");
    }

    private Role createRole(final RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        return role;
    }
}