package com.readit.service.impl;

import com.readit.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@PropertySource(value = "classpath:db.properties")
public class FilesServiceImpl implements FilesService {

    @Autowired
    private Environment env;

    public byte[] getImageByteArray(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(getImagesDir() + fileName);
        byte[] fileBytes = new byte[inputStream.available()];
        inputStream.read(fileBytes);
        return fileBytes;
    }

    private String getDataDir() {
        return System.getenv("OPENSHIFT_DATA_DIR");
    }

    private String getImagesDir() {
        if (getDataDir() != null) {
            return getDataDir() + "/images/";
        } else {
            return env.getProperty("images.path");
        }
    }
}
