package com.readit.service.impl;

import com.readit.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${images.path}")
    private String imagesPath;

    public byte[] findImageByteArray(String fileName) throws IOException {
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
            return imagesPath;
        }
    }
}
