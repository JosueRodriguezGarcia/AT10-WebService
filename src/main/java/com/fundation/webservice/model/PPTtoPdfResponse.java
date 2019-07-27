/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.model;

/**
 * This class serves as unit test class for Keyframe class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */

public class PPTtoPdfResponse {
    private String fileName;
    private String fileDownloadUri;
    private String checksum;
    
    public PPTtoPdfResponse(String fileName, String fileDownloadUri, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.checksum = checksum;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getChecksum() {
        return checksum;
    }
}
