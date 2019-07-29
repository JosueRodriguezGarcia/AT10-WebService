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
 * Implements a type of response to an /upload request.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Jesus Menacho
 * @version 1.1
 */
public class VideoResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;

    //Audio  variables.
    private String audioCodec;
    private String audioBitRate;
    private String audioChannel;

     //Video variables.
    private String videoCodec;
    private String videoBitRate;
    private String fps;

    //Metadata Variable.
    private String metadata;

    //Thumbnail Variables.
    private String thumbnail;

    //Keyframe Variables.
    private String keyframes;

    //Checksum variables.
    private String checksum;

    /**
     * @param fileName defines de name os input file.
     * @param fileDownloadUri defines destination URL direction.
     * @param fileType defines de extention of the input file.
     * @param size defines the size of the file.
     * @param newFormat defines the output format file.
     * @param audioCodec defines the audio codec format.
     * @param audioBitRate defines the bit rate of the output file.
     * @param audioChannel defines the number of channels required in the output file.
     * @param videoCodec defines the video codec format of the output video file.
     * @param videoBitRate defines the video bit rate.
     * @param fps defines the rate of the output video file.
     * @param checksum defines the checksum of the output or input file.
     */
    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size, String newFormat,
                         String audioCodec, String audioBitRate, String audioChannel, String videoCodec,
                         String videoBitRate, String fps, String metadata, String thumbnail, String keyframes,
                         String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;

        //Audio constructors.
        this.audioCodec = audioCodec;
        this.audioBitRate = audioBitRate;
        this.audioChannel = audioChannel;

        //Video constructors.
        this.videoCodec = videoCodec;
        this.videoBitRate = videoBitRate;
        this.fps = fps;

        //Metadata constructor.
        this.metadata = metadata;

        //Thumbnail constructors.
        this.thumbnail = thumbnail;

        //Keyframe constructor.
        this.keyframes =  keyframes;

        //Checksum constructor.
        this.checksum = checksum;
    }

    /**
     * Method that uses for send the file name of the input file.
     *
     * @return file name of the input file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Method that uses for destination URL direction.
     *
     * @return destination URL direction.
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     * Method that uses for extention of the input file.
     *
     * @return extention of the input file.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Method that uses for size of the file.
     *
     * @return size of the file.
     */
    public long getSize() {
        return size;
    }

    /**
     * Method that uses for output format file.
     *
     * @return output format file.
     */
    public String getNewFormat() {
        return newFormat;
    }

    /**
     * Method that uses for audio codec format.
     *
     * @return audio codec format.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Method that uses for bit rate of the output file.
     *
     * @return bit rate of the output file.
     */
    public String getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Method that uses for number of channels required in the output file.
     *
     * @return number of channels required in the output file.
     */
    public String getAudioChannel() {
        return audioChannel;
    }

    /**
     * Method that uses for video codec format of the output video file.
     *
     * @return video codec format of the output video file.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Method that uses for video bit rate.
     *
     * @return video bit rate.
     */
    public String getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * Method that uses for rate of the output video file.
     *
     * @return rate of the output video file.
     */
    public String getFps() {
        return fps;
    }

    /**
     * Method that uses for metadata of the output file.
     *
     * @return
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Method that uses for thumbnail of the output file
     *
     * @return
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Method that uses for keyframe of the output file.
     *
     * @return
     */
    public String getKeyframes() {
        return keyframes;
    }

    /**
     * Method that uses for checksum of the output or input file.
     *
     * @return checksum of the output or input file.
     */
    public String getChecksum(){
        return checksum;
    }
}
