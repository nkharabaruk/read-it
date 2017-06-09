package com.readit.service.impl;

import com.readit.entity.Profile;
import com.readit.repository.ProfileRepository;
import com.readit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findById(long id) {
        return profileRepository.findOne(id);
    }

    @Override
    public List<Profile> saveAll(List<Profile> list) {
        return profileRepository.save(list);
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteAll() {
        profileRepository.deleteAll();
    }

    @Override
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }
}
