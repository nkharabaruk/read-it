package com.readit.service;

import java.io.IOException;

public interface FilesService {

    byte[] findImageByteArray(String fileName) throws IOException;
}
