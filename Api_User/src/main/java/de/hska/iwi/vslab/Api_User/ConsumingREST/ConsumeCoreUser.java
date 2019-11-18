package de.hska.iwi.vslab.Api_User.ConsumingREST;

import org.springframework.web.client.RestTemplate;


public class ConsumeCoreUser {

    private String urlCoreUser = "http://localhost:8083/user";
    
    RestTemplate restTemplate = new RestTemplate();

    public User[] getAllUsers() {
        return restTemplate.getForObject(urlCoreUser, User[].class);
    }

    public User getUser(String input) {
        return restTemplate.getForObject(urlCoreUser + "/" + input, User.class);
    }

    public void addUser(String firstname, String lastname, String username, String password, int roleId) {
        User user = new User(firstname, lastname, username, password, roleId);
        restTemplate.postForLocation(urlCoreUser, user);
    }

    public void updateUser(User user) {
        restTemplate.put(urlCoreUser, user);
    }

    public void deleteAllUsers() {
        restTemplate.delete(urlCoreUser);
    }

    public void deleteUser(int id) {
        restTemplate.delete(urlCoreUser + "/" + id);
    }



}