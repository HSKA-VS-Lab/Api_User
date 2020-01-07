package de.hska.iwi.vslab.Api_User.Controllers;

import de.hska.iwi.vslab.Api_User.Services.ApiUserService;
import de.hska.iwi.vslab.Api_User.ConsumingREST.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @GetMapping("/user")
    public User[] getAllUsers() {
        return apiUserService.getAllUsers();
    }

    @GetMapping("/user/{input}")
    public User getUser(@PathVariable String input) {
        return apiUserService.getUser(input);
    }

    @PostMapping(path="/user", consumes="application/json")
    public void addUser(@RequestBody(required=true) User payload) {
        log.info("register(firstname, lastname, username, password) was called. Payload:", payload.toString());
        apiUserService.register(payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword());
    }

    @PutMapping(path="/user/{id}", consumes="application/json")
    public void updateUser(@PathVariable int id,  @RequestBody(required=true) User payload) {
        log.info("updateUser(roleId, firstname, lastname, username, password) was called. Payload:", payload);
        apiUserService.updateUser(id, payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword(), payload.getRoleId());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        apiUserService.deleteUser(id);
    }

}
