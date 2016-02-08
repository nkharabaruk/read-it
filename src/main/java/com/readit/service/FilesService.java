package com.readit.service;

import java.io.IOException;

public interface FilesService {

    public byte[] getImageByteArray(String fileName) throws IOException;
}
