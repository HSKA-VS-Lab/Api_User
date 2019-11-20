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

    @PostMapping("/user")
    public void addUser(@RequestBody String firstname, String lastname, String username, String password) {
        log.info("register(firstname, lastname, username, password) was called");
        apiUserService.register(firstname, lastname, username, password);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable int roleId, @RequestBody String firstname, String lastname, String username, String password) {
        apiUserService.updateUser(roleId, firstname, lastname, username, password);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        apiUserService.deleteUser(id);
    }

}
