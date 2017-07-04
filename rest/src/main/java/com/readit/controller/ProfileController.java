package com.readit.controller;

import com.readit.controller.exception.AlreadyExistsException;
import com.readit.controller.exception.NotFoundException;
import com.readit.entity.Profile;
import com.readit.service.ProfileService;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/{profileId}")
    public Profile getProfileById(@PathVariable long profileId) {
        try {
            return profileService.findById(profileId);
        } catch (ProfileNotFoundException e) {
            throw new NotFoundException("Profile doesn`t exist.");
        }
    }

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) {
        try {
            return profileService.save(profile);
        } catch (ProfileAlreadyExistsException e) {
            throw new AlreadyExistsException("Profile already exist.");
        }
    }

    @DeleteMapping("/all")
    public void deleteAllProfiles() {
        profileService.deleteAll();
    }

    @DeleteMapping
    public void deleteProfile(@RequestBody Profile profile) {
        try {
            profileService.delete(profile);
        } catch (ProfileNotFoundException e) {
            throw new NotFoundException("Profile doesn`t exist.");
        }
    }
}
