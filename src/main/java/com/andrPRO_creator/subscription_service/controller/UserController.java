package com.andrPRO_creator.subscription_service.controller;

import com.andrPRO_creator.subscription_service.dto.request.UserRequest;
import com.andrPRO_creator.subscription_service.entity.User;
import com.andrPRO_creator.subscription_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        logger.info("Requesting a user with an ID: {}", id);
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserRequest request) {
        logger.info("Request to create a new user");
        userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @RequestBody UserRequest request) {
        logger.info("Request to change parameters of user with an ID: {}", id);
        userService.updateUser(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Request to delete user with an ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
