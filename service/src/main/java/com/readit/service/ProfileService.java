package com.readit.service;

import com.readit.entity.Profile;
import com.readit.service.exception.ProfileAlreadyExistsException;
import com.readit.service.exception.ProfileNotFoundException;

import java.util.List;

public interface ProfileService {

    List<Profile> findAll();

    Profile findById(long id) throws ProfileNotFoundException;

    List<Profile> saveAll(List<Profile> list);

    Profile save(Profile profile) throws ProfileAlreadyExistsException;

    void deleteAll();

    void delete(long id) throws ProfileNotFoundException;
}
