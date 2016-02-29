package com.readit.service;

import java.io.IOException;

public interface FilesService {

    byte[] getImageByteArray(String fileName) throws IOException;
}
