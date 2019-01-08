package com.radek.travelplanet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties = {"management.health.mail.enabled=false"})
public class TravelplanetApplicationTests {

    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void contextLoads() {
    }

}
