package com.readit.controller;

import com.readit.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebMvc
@RestController
public class FilesController {

    private final FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/images/**")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getServletPath().substring("/images/".length(), request.getServletPath().length());
        byte[] imageBytes = filesService.getImageByteArray(fileName);
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}