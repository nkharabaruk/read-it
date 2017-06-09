package com.readit.service;

import com.readit.entity.Settings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettingsService {

    List<Settings> findAll();

    Settings findById(long id);

    List<Settings> saveAll(List<Settings> list);

    Settings save(Settings settings);

    void deleteAll();

    void delete(Settings settings);
}
