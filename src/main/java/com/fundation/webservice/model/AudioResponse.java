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
 * Implements a type of response to an /upload request.
 *
 * @author Maday Alcala Cuba, Jess Menacho
 * @version 1.0
 */
public class AudioResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;
    private String aCodec;
    private String aBitRate;
    private String aChannel;

    private String checksum;

    public AudioResponse(String fileName, String fileDownloadUri, String fileType, long size,
                         String newFormat, String aCodec, String aBitRate, String aChannel, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;
        this.aCodec = aCodec;
        this.aBitRate = aBitRate;
        this.aChannel = aChannel;
        this.checksum = checksum;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public long getSize() {
        return size;
    }

    public String getNewFormat() {
        return newFormat;
    }

    public String getaCodec() {
        return aCodec;
    }

    public String getaBitRate() {
        return aBitRate;
    }

    public String getaChannel() {
        return aChannel;
    }

    public String getChecksum() {
        return checksum;
    }
}
