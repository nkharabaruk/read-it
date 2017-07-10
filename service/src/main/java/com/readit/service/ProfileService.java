package com.readit.service;

import com.readit.entity.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> findAll();

    Profile findById(long id);

    List<Profile> saveAll(List<Profile> list);

    Profile save(Profile profile);

    Profile update(long id, Profile profile);

    void deleteAll();

    void delete(long id);
}
