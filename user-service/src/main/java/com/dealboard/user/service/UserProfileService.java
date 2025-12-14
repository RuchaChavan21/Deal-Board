package com.dealboard.user.service;

import com.dealboard.user.entity.UserProfile;
import com.dealboard.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile getProfile(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
    }

    public UserProfile createProfile(Long userId, String name, String email) {
        UserProfile profile = new UserProfile(userId, name, email);
        return repository.save(profile);
    }

    public UserProfile updateProfile(Long userId, UserProfile updated) {

        UserProfile existing = getProfile(userId);
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setProfileImageUrl(updated.getProfileImageUrl());

        return repository.save(existing);
    }
}
