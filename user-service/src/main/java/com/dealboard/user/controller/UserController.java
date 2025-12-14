package com.dealboard.user.controller;

import com.dealboard.user.entity.UserProfile;
import com.dealboard.user.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserProfileService service;

    public UserController(UserProfileService service) {
        this.service = service;
    }

    // Get current user profile
    @GetMapping("/me")
    public UserProfile getMyProfile(
            @RequestHeader("X-USER-ID") Long userId) {

        return service.getProfile(userId);
    }

    // Create profile (called once after registration)
    @PostMapping("/me")
    public UserProfile createProfile(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody UserProfile profile) {

        return service.createProfile(
                userId,
                profile.getName(),
                profile.getEmail()
        );
    }

    // Update profile
    @PutMapping("/me")
    public UserProfile updateProfile(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody UserProfile profile) {

        return service.updateProfile(userId, profile);
    }
}
