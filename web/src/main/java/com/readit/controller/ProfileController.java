package com.readit.controller;

import com.readit.entity.Profile;
import com.readit.entity.Book;
import com.readit.service.ProfileService;
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
        return  profileService.findAll();
    }

    @GetMapping("/{profileId}")
    public Profile getProfileById(@PathVariable long profileId) {
        return profileService.findById(profileId);
    }

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) {
        return profileService.save(profile);
    }

    @DeleteMapping("/all")
    public void deleteAllProfiles() {
        profileService.deleteAll();
    }

    @DeleteMapping
    public void deleteProfile(@RequestBody Profile profile) {
        profileService.delete(profile);
    }
}
