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
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas
 * @version 1.1
 */
public class VideoResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;
    /**
     * Audio  variables.
     */
    private String audioCodec;
    private String audioBit;
    private String audioChannel;
    private String audioRate;
    /**
     * Video variables.
     */
    private String videoCodec;
    private String videoTag;
    private String videoBit;
    private String videoRate;
    private String checksum;

    /**
     *
     * @param fileName defines de name os input file.
     * @param fileDownloadUri defines destination URL direction.
     * @param fileType defines de extention of the input file.
     * @param size defines the size of the file.
     * @param newFormat defines the output format file.
     * @param audioCodec defines the audio codec format.
     * @param audioBit defines the bit rate of the output file.
     * @param audioChannel defines the number of channels required in the output file.
     * @param audioRate defines the audio rate of the output file.
     * @param videoCodec defines the video codec format of the output video file.
     * @param videoTag defines the video tag of the output video file.
     * @param videoBit defines the video bit rate.
     * @param videoRate defines the rate of the output video file.
     * @param checksum defines the checksum of the output or input file.
     */
    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size,
                         String newFormat, String audioCodec, String audioBit, String audioChannel, String audioRate,
                         String videoCodec, String videoTag, String videoBit, String videoRate, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;

        /**
         * Audio constructors.
         */
        this.audioCodec = audioCodec;
        this.audioBit = audioBit;
        this.audioChannel = audioChannel;
        this.audioRate = audioRate;

        /**
         * Audio constructors.
         */
        this.videoCodec = videoCodec;
        this.videoTag = videoTag;
        this.videoBit = videoBit;
        this.videoRate = videoRate;
        this.checksum = checksum;
    }

    /**
     * Uses for send the file name of the input file.
     *
     * @return file name of the input file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Uses for destination URL direction.
     *
     * @return destination URL direction.
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     * Uses for extention of the input file.
     *
     * @return extention of the input file.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Uses for size of the file.
     *
     * @return size of the file.
     */
    public long getSize() {
        return size;
    }

    /**
     * Uses for output format file.
     *
     * @return output format file.
     */
    public String getNewFormat() {
        return newFormat;
    }

    /**
     * Uses for audio codec format.
     *
     * @return audio codec format.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Uses for bit rate of the output file.
     *
     * @return bit rate of the output file.
     */
    public String getAudioBit() {
        return audioBit;
    }

    /**
     * Uses for number of channels required in the output file.
     *
     * @return number of channels required in the output file.
     */
    public String getAudioChannel() {
        return audioChannel;
    }

    /**
     * Uses for audio rate of the output file.
     *
     * @return audio rate of the output file.
     */
    public String getAudioRate() {
        return audioRate;
    }

    /**
     * Uses for video codec format of the output video file.
     *
     * @return video codec format of the output video file.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Uses for video tag of the output video file.
     *
     * @return video tag of the output video file.
     */
    public String getVideoTag() {
        return videoTag;
    }

    /**
     * Uses for video bit rate.
     *
     * @return video bit rate.
     */
    public String getVideoBit() {
        return videoBit;
    }

    /**
     * Uses for rate of the output video file.
     *
     * @return rate of the output video file.
     */
    public String getVideoRate() {
        return videoRate;
    }

    /**
     * Uses for checksum of the output or input file.
     *
     * @return checksum of the output or input file.
     */
    public String getChecksum(){ return checksum; }
}
