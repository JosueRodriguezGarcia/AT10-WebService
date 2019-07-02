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

/**
 * Implements a type of response to an /upload request.
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
public class VideoResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String vcodec;
    private String acodec;
    private String container;
    private String frameRate;
    private String width;
    private String height;

    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size, 
            String vcodec, String acodec, String container, String frameRate, 
            String width, String height) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.vcodec = vcodec;
        this.acodec = acodec;
        this.container = container;
        this.frameRate = frameRate;
        this.width = width;
        this.height = height;
    }

    // Getters needed so Spring Boot Framework can return object as JSON
    public String getfileName() {
        return this.fileName;
    }

    public String getFileDownloadUri() {
        return this.fileDownloadUri;
    }

    public String getFileType() {
        return this.fileType;
    }

    public long getSize() {
        return this.size;
    }

    public String getVcodec() {
        return this.vcodec;
    }

    public String getAcodec() {
        return this.acodec;
    }

    public String getContainer() {
        return this.container;
    }

    public String getFrameRate() {
        return this.frameRate;
    }

    public String getWidth() {
        return this.width;
    }

    public String getHeight() {
        return this.height;
    }
}
