package com.readit.service.impl;

import com.readit.entity.Settings;
import com.readit.repository.SettingsRepository;
import com.readit.service.SettingsService;
import com.readit.service.exception.SettingsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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
        return settingsRepository.findById(id).orElseThrow(() -> new SettingsNotFoundException(id));
    }

    @Override
    public List<Settings> saveAll(List<Settings> list) {
        return settingsRepository.saveAll(list);
    }

    @Override
    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    @Override
    public Settings update(long id, Settings settings) {
        try {
            Settings existing = settingsRepository.getOne(id);
            settings.setId(existing.getId());
            return settingsRepository.save(settings);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new SettingsNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        settingsRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            settingsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new SettingsNotFoundException(id);
        }
    }
}
