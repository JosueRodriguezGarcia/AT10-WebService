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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Implements a POJO class to be binded to a specific prefix in application.properties.
 * In this case prefix=file, this is to be used for storing both uploaded and converted assets.
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
@ConfigurationProperties(prefix = "file")
public class StorageProperties {
    private String uploadDir;
    private String conversionDir;

    /**
     * Getters AND setters are required by the @ConfigurationProperties annotation.
     */
    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getConversionDir() {
        return conversionDir;
    }

    public void setConversionDir(String downloadDir) {
        this.conversionDir = downloadDir;
    }
}
