/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

/**
 * Implements the REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
@RestController
public class Controller {
    @Autowired
    private StorageService storageService;

    @RequestMapping("/")
    public String home() {
        return "AT-10 File Conversion Service";
    }
    
    @RequestMapping("/hello")
    public Greeting hello() {
        return new Greeting("Welcome", "visitor");
    }
    
    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "content", defaultValue = "Greetings") String content, 
                     @RequestParam(value = "name", defaultValue = "earthling") String name) {
        Greeting greeting = new Greeting(content, name);
        return greeting.getContent() + " " + greeting.getName() + "!";
    }

    @PostMapping("/upload")
    public VideoResponse upload(@RequestParam("file") MultipartFile file, 
                                         @RequestParam(value = "vcodec", defaultValue = "") String vcodec, 
                                         @RequestParam(value = "acodec", defaultValue = "") String acodec, 
                                         @RequestParam(value = "container", defaultValue = "") String container, 
                                         @RequestParam(value = "frameRate", defaultValue = "") String frameRate, 
                                         @RequestParam(value = "width", defaultValue = "") String width, 
                                         @RequestParam(value = "height", defaultValue = "") String height) {
        String fileName = storageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                 .path("/download/")
                                 .path(fileName)
                                 .toUriString();

        return new VideoResponse(fileName, fileDownloadUri, file.getContentType(), 
                                      file.getSize(), vcodec, acodec, container, frameRate, width, 
                                      height);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = storageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } 
        catch (Exception ex) {
            
        }

        // Default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
                resource.getFilename() + "\"").body(resource);
    }
}
