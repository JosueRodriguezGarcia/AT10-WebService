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
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Jesus Menacho.
 * @version 1.1
 */

public class WordToPdfResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String checksum;

    /**
     * This constructor let setting variables.
     * @param fileName this is parameter the file name.
     * @param fileDownloadUri this is the
     * @param checksum
     */
    public WordToPdfResponse(String fileName, String fileDownloadUri, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.checksum = checksum;
    }

    /**
     * This method get a file name.
     * @return the name file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method let setting a fileName.
     * @param fileName return the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method let get a file.
     * @return the file variable.
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     * This method let get fileDownloadUri.
     * @param fileDownloadUri the directions to the file.
     */
    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    /**
     * This variable let get a checksum.
     * @return the checksum variable.
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * This method let setting a variable checksum.
     * @param checksum this varable the checksum that have a file.
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
