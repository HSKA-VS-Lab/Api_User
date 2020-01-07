package de.hska.iwi.vslab.Api_User.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumeCompUserRole {

    //private String urlCompUserRole = "http://localhost:8085//comp_user_role/user";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCompUserRole.class);
    RestTemplate restTemplate = new RestTemplate();

    public void register(String firstname, String lastname, String username, String password) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getRegisterUrl());
            HttpEntity<User> request = new HttpEntity<>(new User(firstname, lastname, username, password, 2));
            restTemplate.postForLocation(urlBuilder.getRegisterUrl(), request);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

}