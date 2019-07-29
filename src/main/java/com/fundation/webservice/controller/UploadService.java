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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Implements a Spring service that handles copying to the upload directory.
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
@Service
public class UploadService {
    private final Path uploadLocation;

    @Autowired
    public UploadService(StorageProperties storageProperties) {
        this.uploadLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadLocation);
        } 
        catch (Exception ex) {
            throw new IllegalStateException("Could not create the upload directory.", ex);
        }
    }

    // Stores assets that are uploaded through /upload endpoint.
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new IllegalStateException("Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.uploadLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } 
        catch (IOException ex) {
            throw new IllegalStateException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
