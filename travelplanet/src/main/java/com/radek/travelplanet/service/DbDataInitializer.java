package com.radek.travelplanet.service;

import com.radek.travelplanet.model.*;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.repository.RoleRepository;
import com.radek.travelplanet.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DbDataInitializer implements CommandLineRunner {

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
    public void run(String... strings) {
        log.info("Populating sample data in db.");

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
        offer.setFrequency("4");
        offer.setLink("https://www.itaka.pl/wczasy/tunezja/djerba/hotel-zita-beach-resort-zarzis,DJEZITA.html?ofr_id=56e99ed144905eeef41c568573095cb932defbd00c7f56290a3201b108bd4fe3");
        offer.setName("ITAKA Shit Week");
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offer.setUserAccount(admin);
        offerRepository.save(offer);

        log.info("Data populated in db.");
    }

    private Role createRole(final RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        return role;
    }
}
