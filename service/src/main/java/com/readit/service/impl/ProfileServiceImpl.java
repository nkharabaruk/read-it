package com.readit.service.impl;

import com.readit.entity.Profile;
import com.readit.repository.ProfileRepository;
import com.readit.service.ProfileService;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;
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
    public Profile findById(long id) throws ProfileNotFoundException {
        Profile author = profileRepository.findOne(id);
        if (author == null) {
            throw new ProfileNotFoundException();
        } else {
            return author;
        }
    }

    @Override
    public List<Profile> saveAll(List<Profile> list) {
        return profileRepository.save(list);
    }

    @Override
    // TODO: to test it
    public Profile save(Profile profile) throws ProfileAlreadyExistsException {
        List<Profile> profilesInDB = profileRepository
                .findByWasReadAndIsReadingAndWantToRead(
                        profile.getWasRead(),
                        profile.getIsReading(),
                        profile.getWantToRead());
        if (!profilesInDB.isEmpty()){
            for (Profile prof : profilesInDB) {
                if (prof.equals(profile)) {
                    throw new ProfileAlreadyExistsException();
                }
            }
        }
        return profileRepository.save(profile);
    }

    @Override
    public void deleteAll() {
        profileRepository.deleteAll();
    }

    @Override
    public void delete(long id) throws ProfileNotFoundException {
        if (profileRepository.findOne(id) != null) {
            profileRepository.delete(id);
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
