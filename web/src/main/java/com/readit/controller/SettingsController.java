package com.readit.controller;

import com.readit.entity.Settings;
import com.readit.service.SettingsService;
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
        return  settingsService.findAll();
    }

    @GetMapping("/{settingsId}")
    public Settings getSettingsById(@PathVariable long settingsId) {
        return settingsService.findById(settingsId);
    }

    @PostMapping
    public Settings saveSettings(@RequestBody Settings settings) {
        return settingsService.save(settings);
    }

    @DeleteMapping("/all")
    public void deleteAllSettings() {
        settingsService.deleteAll();
    }

    @DeleteMapping
    public void deleteSettings(@RequestBody Settings settings) {
        settingsService.delete(settings);
    }
}
