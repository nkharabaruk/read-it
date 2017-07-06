package com.readit.service.impl;

import com.readit.entity.Settings;
import com.readit.repository.SettingsRepository;
import com.readit.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Override
    public List<Settings> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    public Settings findById(long id) {
        return settingsRepository.findOne(id);
    }

    @Override
    public List<Settings> saveAll(List<Settings> list) {
        return settingsRepository.save(list);
    }

    @Override
    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    @Override
    public void deleteAll() {
        settingsRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        settingsRepository.delete(id);
    }
}
