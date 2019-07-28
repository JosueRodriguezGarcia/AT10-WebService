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
 * API Response after a successful video transcoding.
 * Returns ouput file information as a JSON formatted string.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Jesus Menacho
 * @version 1.5
 */
public class VideoResponse extends Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;

    /**
     * Audio  variables.
     */
    private String audioCodec;
    private String audioBitRate;
    private String audioChannel;

    /**
     * Video variables.
     */
    private String videoCodec;
    private String videoBitRate;
    private String fps;

    /**
     * Metadata Variable.
     */
    private String metadata;

    /**
     * Thumbnail Variables.
     */
    private boolean thumbnail;

    /**
     * Keyframe Variables.
     */
    private boolean keyframes;

    /**
     * Checksum variables.
     */
    private String checksum;

    /**
     * Class Constructor
     *
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
     * @param metadata defines wether the metadata will be provided as xmp or json data. If not specified by the client,
     *                 it will not return any metadata info
     * @param thumbnail defines whether the transcoding process provides a thumbnail for the video file for a specific
     *                  time position in the video.
     * @param keyframes defines whether the transcoding process provides a set of keyframe images for every n time
     *                  posision within the video file
     * @param checksum provides the checksum of the output file.
     */
    public VideoResponse(String fileName, String fileDownloadUri, String fileType, long size,
                         String newFormat, String audioCodec, String audioBitRate, String audioChannel,
                         String videoCodec, String videoBitRate, String fps, String metadata, boolean thumbnail,
                         boolean keyframes, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;

        /**
         * Audio constructors.
         */
        this.audioCodec = audioCodec;
        this.audioBitRate = audioBitRate;
        this.audioChannel = audioChannel;

        /**
         * Audio constructors.
         */
        this.videoCodec = videoCodec;
        this.videoBitRate = videoBitRate;
        this.fps = fps;

        /**
         * Metadata constructor.
         */
        this.metadata = metadata;

        /**
         * Thumbnail constructors.
         */
        this.thumbnail = thumbnail;

        /**
         * Keyframe constructor.
         */
        this.keyframes = keyframes;

        /**
         * Checksum constructor.
         */
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
    public String getAudioBitRate() {
        return audioBitRate;
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
     * Uses for video codec format of the output video file.
     *
     * @return video codec format of the output video file.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Uses for video bit rate.
     *
     * @return video bit rate.
     */
    public String getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * Uses for rate of the output video file.
     *
     * @return rate of the output video file.
     */
    public String getFps() {
        return fps;
    }

    /**
     * Uses for metadata of the output file.
     *
     * @return
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Uses for thumbnail of the output file
     *
     * @return
     */
    public boolean getThumbnail() {
        return thumbnail;
    }

    /**
     * Uses for keyframe of the output file
     *
     * @return
     */
    public boolean getKeyframes() {
        return keyframes;
    }

    /**
     * Uses for checksum of the output or input file.
     *
     * @return checksum of the output or input file.
     */

    public String getChecksum() {
        return checksum;
    }
}
