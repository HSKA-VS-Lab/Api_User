package de.hska.iwi.vslab.Api_User.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


public class ConsumeCoreUser {

    //private String urlCoreUser = "http://localhost:8083/user";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreUser.class);
    RestTemplate restTemplate = new RestTemplate();

    public User[] getAllUsers() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl_core_user());
            return restTemplate.getForObject(urlBuilder.getUserUrl(), User[].class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public User getUser(String input) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getInputUrl(input));
            return restTemplate.getForObject(urlBuilder.getInputUrl(input), User.class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void addUser(String firstname, String lastname, String username, String password, int roleId) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl_core_user());
            User user = new User(firstname, lastname, username, password, roleId);
            restTemplate.postForLocation(urlBuilder.getSlashURL_core(), user);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void updateUser(User user) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getUrlWithId_core(user.getId()));
            restTemplate.put(urlBuilder.getUrlWithId_core(user.getId()), user);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteAllUsers() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl_core_user());
            restTemplate.delete(urlBuilder.getSlashURL_core());
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteUser(int id) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getUrlWithId_core(id));
            restTemplate.delete(urlBuilder.getUrlWithId_core(id));
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }



}