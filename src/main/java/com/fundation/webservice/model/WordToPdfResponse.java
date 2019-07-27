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
 * Implements a type of response to an /upload request.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Jesus Menacho
 * @version 1.1
 */

public class WordToPdfResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String checksum;

    public WordToPdfResponse(String fileName, String fileDownloadUri, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.checksum = checksum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
