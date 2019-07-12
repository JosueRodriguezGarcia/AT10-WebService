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
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba
 * @version 1.1
 */
public class VideoResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;
    //audio
    private String aCodec;
    private String aBit;
    private String aChannel;
    private String aRate;
    //video
    private String vCodec;
    private String vTag;
    private String vBit;
    private String vRate;

    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size,
            String newFormat, String aCodec, String aBit, String aChannel, String aRate,
            String vCodec, String vTag, String vBit, String vRate) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;
        this.aCodec = aCodec;
        this.aBit = aBit;
        this.aChannel = aChannel;
        this.aRate = aRate;
        this.vCodec = vCodec;
        this.vTag = vTag;
        this.vBit = vBit;
        this.vRate = vRate;
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

    public String getaBit() {
        return aBit;
    }

    public String getaChannel() {
        return aChannel;
    }

    public String getaRate() {
        return aRate;
    }

    public String getvCodec() {
        return vCodec;
    }

    public String getvTag() {
        return vTag;
    }

    public String getvBit() {
        return vBit;
    }

    public String getvRate() {
        return vRate;
    }
}
