/**
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
 * @author Josue Rodriguez Garcia, Jesús Menacho
 * @version 1.0
 */

public class KeyFrameResponse {
    private String fileName;
    private String fileDownloadUri;

    /**
     *  This constructor let me setting the variables filenName, fileDownlaodUri.
     * @param fileName
     * @param fileDownloadUri
     */
    public KeyFrameResponse(String fileName, String fileDownloadUri) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
    }

    /**
     * This method return fileName.
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method return the fileDownloadUri.
     * @return
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }
}
