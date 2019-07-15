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
    private String audioCodec;
    private String audioBit;
    private String audioChannel;
    private String audioRate;
    //video
    private String videoCodec;
    private String videoTag;
    private String videoBit;
    private String videoRate;

    private String checksum;

    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size,
                         String newFormat, String audioCodec, String audioBit, String audioChannel, String audioRate,
                         String videoCodec, String videoTag, String videoBit, String videoRate, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;
        this.audioCodec = audioCodec;
        this.audioBit = audioBit;
        this.audioChannel = audioChannel;
        this.audioRate = audioRate;
        this.videoCodec = videoCodec;
        this.videoTag = videoTag;
        this.videoBit = videoBit;
        this.videoRate = videoRate;
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

    public String getAudioCodec() {
        return audioCodec;
    }

    public String getAudioBit() {
        return audioBit;
    }

    public String getAudioChannel() {
        return audioChannel;
    }

    public String getAudioRate() {
        return audioRate;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public String getVideoTag() {
        return videoTag;
    }

    public String getVideoBit() {
        return videoBit;
    }

    public String getVideoRate() {
        return videoRate;
    }

    public String getChecksum(){ return checksum; }
}
