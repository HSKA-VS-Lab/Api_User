package de.hska.iwi.vslab.Api_User.ConsumingREST;

import org.springframework.web.client.RestTemplate;

public class ConsumeCompUserRole {

    private String urlCompUserRole = "http://localhost:8085//comp_user_role/user";
    
    RestTemplate restTemplate = new RestTemplate();

    public void register(String firstname, String lastname, String username, String password) {
        restTemplate.postForLocation(urlCompUserRole, firstname, lastname, username, password);
    }

}