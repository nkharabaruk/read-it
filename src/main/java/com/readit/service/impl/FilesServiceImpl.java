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
        String pathToFile;
        if (System.getenv("OPENSHIFT_DATA_DIR") != null) {
            pathToFile = System.getenv("OPENSHIFT_DATA_DIR") + "/images/";
        }
        else {
            pathToFile = env.getProperty("images.path");
        }
        InputStream inputStream = new FileInputStream(pathToFile + fileName);
        byte[] fileBytes = new byte[inputStream.available()];
        inputStream.read(fileBytes);
        return fileBytes;
    }
}
