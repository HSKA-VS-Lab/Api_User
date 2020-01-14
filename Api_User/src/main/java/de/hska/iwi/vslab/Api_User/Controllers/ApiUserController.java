package de.hska.iwi.vslab.Api_User.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vslab.Api_User.Services.ApiUserService;
import de.hska.iwi.vslab.Api_User.ConsumingREST.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableCircuitBreaker
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @GetMapping("/user")
    @HystrixCommand(fallbackMethod = "getFallbackUsers")
    public User[] getAllUsers() {
        return apiUserService.getAllUsers();
    }

    public User[] getFallbackUsers() {
        User user1 = new User("userFallback1", "max", "mustermann","pw123", 0);
        User user2 = new User("userFallback2", "marie", "musterfrau","pw123", 0);
        User[] userA = new User[2];
        userA[0] = user1;
        userA[1] = user2;
        return userA;
    }

    @GetMapping("/user/{input}")
    @HystrixCommand(fallbackMethod = "fallbackGetUser")
    public User getUser(@PathVariable String input) {
        return apiUserService.getUser(input);
    }

    public User fallbackGetUser() {
        User user = new User("userFallback1", "max", "mustermann","pw123", 0);
        return user;
    }

    @PostMapping(path="/user", consumes="application/json")
    @HystrixCommand(fallbackMethod = "defaultFallback")
    public void addUser(@RequestBody(required=true) User payload) {
        log.info("register(firstname, lastname, username, password) was called. Payload:", payload.toString());
        apiUserService.register(payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword());
    }

    @PutMapping(path="/user/{id}", consumes="application/json")
    @HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void updateUser(@PathVariable int id,  @RequestBody(required=true) User payload) {
        log.info("updateUser(roleId, firstname, lastname, username, password) was called. Payload:", payload);
        apiUserService.updateUser(id, payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword(), payload.getRoleId());
    }

    @DeleteMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "defaultFallbackWithId")
    public void deleteUser(@PathVariable int id){
        apiUserService.deleteUser(id);
    }

    public void defaultFallback(Throwable throwable) {
        System.out.printf("DefaultFallback, exception=%s%n", throwable);
    }

    public void defaultFallbackWithId(int id, Throwable throwable) {
        System.out.printf("DefaultFallbackWithId, id=%s, exception=%s%n", id, throwable);
    }
}
