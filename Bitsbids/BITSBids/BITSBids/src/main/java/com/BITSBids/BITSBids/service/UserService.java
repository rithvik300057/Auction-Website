package com.BITSBids.BITSBids.service;

import com.BITSBids.BITSBids.model.User;
import com.BITSBids.BITSBids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Add a new user to the database
    // Add a new user to the database
    public User addUser(User user) {
        // Generate a unique ID for the user
        user.setUid(UUID.randomUUID().toString().split("-")[0]);

        // Parse the 'ucontact' string to an integer
        if (user.getUcontact() != null) {
            try {
                user.setUcontact(Integer.parseInt(String.valueOf(user.getUcontact())));
            } catch (NumberFormatException e) {
                // Handle the error if the 'ucontact' is not a valid integer
            }
        }

        // Save the user to the repository
        return userRepository.save(user);
    }


    // Retrieve a list of all users from the database
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by their unique ID
    public Optional<User> getUserById(String uid) {
        return userRepository.findById(uid);
    }

    // Update an existing user's information by their ID
    public Optional<User> updateUser(String uid, User userRequest) {
        Optional<User> existingUserOptional = userRepository.findById(uid);
        if (existingUserOptional.isPresent()) {
            // Get the existing user from the database
            User existingUser = existingUserOptional.get();
            // Update the user's properties with the new data
            existingUser.setUname(userRequest.getUname());
            existingUser.setUbatch(userRequest.getUbatch());
            existingUser.setUcontact(userRequest.getUcontact());
            existingUser.setUemail(userRequest.getUemail());
            // Save the updated user to the repository
            return Optional.of(userRepository.save(existingUser));
        }
        return Optional.empty(); // User not found
    }

    // Delete a user by their unique ID
    public boolean deleteUser(String uid) {
        Optional<User> user = userRepository.findById(uid);
        if (user.isPresent()) {
            // Delete the user from the repository
            userRepository.deleteById(uid);
            return true; // User was found and deleted
        }
        return false; // User not found
    }

}
