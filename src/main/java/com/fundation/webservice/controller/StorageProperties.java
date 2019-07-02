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
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
@ConfigurationProperties(prefix = "file")
public class StorageProperties {
    private String uploadDir;
    private String downloadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }
}
