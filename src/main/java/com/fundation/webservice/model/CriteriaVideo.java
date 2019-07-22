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
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Maday Alcala, Limbert Vargas
 * @version 1.0
 */
public class CriteriaVideo extends CriteriaConvert{
    private String newFormat;

    /**
     * Audio variables.
     */
    private String audioCodec;
    private int audioBitRate;
    private int audioChannel;
//    private int audioRate;

    /**
     * video variables.
     */
    private String videoCodec;
//    private String videoTag;
    private int videoBitRate;
    private int fps;

    /**
     * Uses for return output format file.
     *
     * @return output format file.
     */
    public String getNewFormat() {
        return newFormat;
    }

    /**
     * Uses for modified output format file.
     *
     * @param newFormat output format file.
     */
    public void setNewFormat(String newFormat) {
        this.newFormat = newFormat;
    }

    /**
     * Uses for return audio codec format.
     *
     * @return audio codec format.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Uses for modified audio codec format.
     *
     * @param audioCodec audio codec format.
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Uses for return bit rate of the output file.
     *
     * @return bit rate of the output file.
     */
    public int getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Uses for modified bit rate of the output file.
     *
     * @param audioBitRate bit rate of the output file.
     */
    public void setAudioBitRate(int audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    /**
     * Uses for return number of channels required in the output file.
     *
     * @return number of channels required in the output file.
     */
    public int getAudioChannel() {
        return audioChannel;
    }

    /**
     * Uses for modified number of channels required in the output file.
     *
     * @param audioChannel number of channels required in the output file.
     */
    public void setAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }

//    /**
//     * Uses for return audio rate of the output file.
//     *
//     * @return audio rate of the output file.
//     */
//    public int getAudioRate() {
//        return audioRate;
//    }
//
//    /**
//     * Uses for modified audio rate of the output file.
//     *
//     * @param audioRate audio rate of the output file.
//     */
//    public void setAudioRate(int audioRate) {
//        this.audioRate = audioRate;
//    }

    /**
     * Uses for return video codec format of the output video file.
     *
     * @return video codec format of the output video file.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Uses for modified video codec format of the output video file.
     *
     * @param videoCodec video codec format of the output video file.
     */
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

//    /**
//     * Uses for return video tag of the output video file.
//     *
//     * @return video tag of the output video file.
//     */
//    public String getVideoTag() {
//        return videoTag;
//    }
//
//    /**
//     * Uses for modified video tag of the output video file.
//     *
//     * @param videoTag video tag of the output video file.
//     */
//    public void setVideoTag(String videoTag) {
//        this.videoTag = videoTag;
//    }

    /**
     * Uses for return video bit rate.
     *
     * @return video bit rate.
     */
    public int getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * Uses for modified video bit rate.
     *
     * @param videoBitRate video bit rate.
     */
    public void setVideoBitRate(int videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    /**
     * Uses for return rate of the output video file.
     *
     * @return rate of the output video file.
     */
    public int getFps() {
        return fps;
    }

    /**
     * Uses for modified rate of the output video file.
     *
     * @param fps rate of the output video file.
     */
    public void setFps(int fps) {
        this.fps = fps;
    }
}
