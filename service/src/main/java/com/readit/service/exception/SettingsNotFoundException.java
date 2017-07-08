package com.readit.service.exception;

import com.readit.entity.Settings;

public class SettingsNotFoundException extends NotFoundException {
    public SettingsNotFoundException(long id) {
        super(Settings.class, id);
    }
}
