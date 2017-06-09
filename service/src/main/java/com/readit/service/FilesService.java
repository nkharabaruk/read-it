package com.readit.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface FilesService {

    byte[] findImageByteArray(String fileName) throws IOException;
}
