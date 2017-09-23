package com.readit.service.impl;

import com.readit.entity.Profile;
import com.readit.repository.ProfileRepository;
import com.readit.service.ProfileService;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(profileRepository.findOne(id))
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @Override
    public List<Profile> saveAll(List<Profile> list) {
        return profileRepository.save(list);
    }

    @Override
    // TODO: to test it
    public Profile save(Profile profile) {
        profileRepository.findByWasReadAndIsReadingAndWantToRead(
                profile.getWasRead(),
                profile.getIsReading(),
                profile.getWantToRead()).stream()
                .filter(prof -> prof.equals(profile))
                .findAny()
                .ifPresent(ProfileAlreadyExistsException::new);
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(long id, Profile profile) {
    Optional.ofNullable(profileRepository.findOne(id))
                .map(prof -> {profile.setId(prof.getId()); return profile;})
                .orElseThrow(() -> new ProfileNotFoundException(id));
        return profileRepository.save(profile);
    }

    @Override
    public void deleteAll() {
        profileRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            profileRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProfileNotFoundException(id);
        }
    }
}
