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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implements a Spring service that handles copying to the upload directory.
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
@Service
public class DownloadService {
    private final Path downloadLocation;

    @Autowired
    public DownloadService(StorageProperties storageProperties) {
        this.downloadLocation = Paths.get(storageProperties.getDownloadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.downloadLocation);
        } 
        catch (Exception ex) {
            throw new IllegalStateException("Could not create the download directory.", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.downloadLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } 
            else {
                throw new IllegalStateException("File not found " + fileName);
            }
        } 
        catch (MalformedURLException ex) {
            throw new IllegalStateException("File not found " + fileName, ex);
        }
    }
}
