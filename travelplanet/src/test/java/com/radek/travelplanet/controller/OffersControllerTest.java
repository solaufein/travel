package com.radek.travelplanet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OffersControllerTest {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<OfferCommand> jsonOfferCommand;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    //    @MockBean
    //    private OfferRepository offerRepository;

    @Test
    public void shouldWatchOfferWithGivenUrl() throws Exception {
        OfferCommand offerCommand = new OfferCommand();
        offerCommand.setUrl("www.onet.pl");
        String content = jsonOfferCommand.write(offerCommand).getJson();

        mvc.perform(post("/travel/offers/watch").with(user("admin").password("admin").roles("ADMIN"))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}