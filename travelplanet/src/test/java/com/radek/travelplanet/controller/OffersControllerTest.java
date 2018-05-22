package com.radek.travelplanet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OffersControllerTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<OfferRequest> jsonOfferRequest;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldWatchOfferWithGivenUrl() throws Exception {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setUrl("https://www.travelplanet.pl/wczasy/hiszpania/costa-del-sol/benalmadena/playa-bonita--benalmadena,28052018RNBW2591.html?box=hiszpania-last-minute");
        String content = jsonOfferRequest.write(offerRequest).getJson();

        mvc.perform(post("/travel/offers/watch").with(user("admin").password("admin").roles("ADMIN"))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Thread.sleep(8000);    //todo: remove
    }

    @Test
    public void shouldFailToWatchOfferFromUnsupportedSite() throws Exception {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setUrl("http://www.onet.pl");
        String content = jsonOfferRequest.write(offerRequest).getJson();

        mvc.perform(post("/travel/offers/watch").with(user("admin").password("admin").roles("ADMIN"))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}