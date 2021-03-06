package com.readit.rest.controller;

import com.readit.entity.Profile;
import com.readit.service.ProfileService;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/profiles")
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
    public Profile getProfileById(@PathVariable long profileId) throws ProfileNotFoundException {
        return profileService.findById(profileId);
    }

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) throws ProfileAlreadyExistsException {
        return profileService.save(profile);
    }

    @PutMapping("/{profileId}")
    public Profile updateProfile(@PathVariable long profileId, @RequestBody Profile profile) throws ProfileNotFoundException {
        return profileService.update(profileId, profile);
    }

    @DeleteMapping
    public void deleteAllProfiles() {
        profileService.deleteAll();
    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable long profileId) throws ProfileNotFoundException {
        profileService.delete(profileId);
    }
}
