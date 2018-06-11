package com.radek.travelplanet.service;

import com.radek.travelplanet.model.*;
import com.radek.travelplanet.repository.UserAccountRepository;
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
    private final UserAccountRepository userAccountRepository;
    private final OfferRepository offerRepository;

    @Autowired
    public DbDataInitializer(RoleRepository roleRepository, UserAccountRepository userAccountRepository, OfferRepository offerRepository) {
        this.roleRepository = roleRepository;
        this.userAccountRepository = userAccountRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("Populating sample data in db.");

        offerRepository.deleteAll();
        userAccountRepository.deleteAll();
        roleRepository.deleteAll();

        Role adminRole = createRole(RoleType.ADMIN);
        Role userRole = createRole(RoleType.USER);
        Role savedAdminRole = roleRepository.save(adminRole);
        Role savedUserRole = roleRepository.save(userRole);

        UserAccount admin = new UserAccount();
        admin.setEmail("admin@gmail.com");
        admin.setFirstName("John");
        admin.setLastName("Smith");
        admin.setState(State.ACTIVE);
        admin.setPassword("admin");
        admin.getRolesSet().add(savedAdminRole);
        userAccountRepository.save(admin);

        UserAccount user = new UserAccount();
        user.setEmail("user@gmail.com");
        user.setFirstName("Jessica");
        user.setLastName("White");
        user.setState(State.ACTIVE);
        user.setPassword("user");
        user.getRolesSet().add(savedUserRole);
        userAccountRepository.save(user);

        Offer offer = new Offer();
        offer.setFrequency("10");
        offer.setLink("www.google.com");
        offer.setName("Spain Week");
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offer.setUserAccount(admin);
        offerRepository.save(offer);

        LOGGER.info("Data populated in db.");
    }

    private Role createRole(final RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        return role;
    }
}
