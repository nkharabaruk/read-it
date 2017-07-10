package com.readit.rest.controller;

import com.readit.entity.Settings;
import com.readit.service.SettingsService;
import com.readit.service.exception.SettingsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public List<Settings> getAllSettings() {
        return settingsService.findAll();
    }

    @GetMapping("/{settingsId}")
    public Settings getSettingsById(@PathVariable long settingsId) {
        return settingsService.findById(settingsId);
    }

    @PostMapping
    public Settings saveSettings(@RequestBody Settings settings) {
        return settingsService.save(settings);
    }

    @PutMapping("/{settingsId}")
    public Settings updateSettings(@PathVariable long settingsId, @RequestBody Settings settings) throws SettingsNotFoundException {
        return settingsService.update(settingsId, settings);
    }

    @DeleteMapping
    public void deleteAllSettings() {
        settingsService.deleteAll();
    }

    @DeleteMapping("/{settingsId}")
    public void deleteSettings(@PathVariable long settingsId) {
        settingsService.delete(settingsId);
    }
}
