/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package com.fundation.webservice.model;

/**
 * Handlends document let me set a get values the keyframe
 *
 * @author Alejandro Sánchez Luizaga, Jesus Menacho
 * @version 1.0
 */
public class KeyFrameResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;
    private String frame;
    private String nameOutputFile;
    private String extOutputFile;
    private String checksum;

    public KeyFrameResponse(String fileName, String fileDownloadUri) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

}
