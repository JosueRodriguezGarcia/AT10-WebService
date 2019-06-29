package com.fundation.webservice.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String conversionDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getConversionDir() {
        return conversionDir;
    }

    public void setConversionDir(String conversionDir) {
        this.conversionDir = conversionDir;
    }
}