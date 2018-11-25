package com.readit.service.impl;

import com.readit.entity.Profile;
import com.readit.repository.ProfileRepository;
import com.readit.service.ProfileService;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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
        return profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @Override
    public List<Profile> saveAll(List<Profile> list) {
        return profileRepository.saveAll(list);
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
        try {
            Profile existed = profileRepository.getOne(id);
            profile.setId(existed.getId());
            return profileRepository.save(profile);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new ProfileNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        profileRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            profileRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProfileNotFoundException(id);
        }
    }
}
