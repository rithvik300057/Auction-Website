package com.BITSBids.BITSBids.controller;

import com.BITSBids.BITSBids.model.User;
import com.BITSBids.BITSBids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Call the service to add a user
        User createdUser = userService.addUser(user);
        // Return a response with the created user and a 201 (CREATED) status code
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Endpoint to get a list of all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        // Call the service to find all users
        List<User> users = userService.findUsers();
        // Return a response with the list of users
        return ResponseEntity.ok(users);
    }

    // Endpoint to get a user by their unique ID
    @GetMapping("/{uid}")
    public ResponseEntity<User> getUserById(@PathVariable String uid) {
        // Call the service to find a user by their ID
        Optional<User> user = userService.getUserById(uid);
        // Check if the user was found and return an appropriate response
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to update an existing user by their ID
    @PutMapping("/{uid}")
    public ResponseEntity<User> updateUser(@PathVariable String uid, @RequestBody User user) {
        // Call the service to update a user
        Optional<User> updatedUser = userService.updateUser(uid, user);
        // Check if the user was found and updated, then return an appropriate response
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to remove a user by their ID
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> removeUser(@PathVariable String uid) {
        // Call the service to delete a user by their ID
        boolean userDeleted = userService.deleteUser(uid);
        // Check if the user was deleted and return an appropriate response
        if (userDeleted) {
            return ResponseEntity.ok("User with ID " + uid + " User Deleted");
        } else {
            // Return a response with a 404 (NOT FOUND) status code and a message
            return new ResponseEntity<>("User with ID " + uid + " Not Found", HttpStatus.NOT_FOUND);
        }
    }

}
