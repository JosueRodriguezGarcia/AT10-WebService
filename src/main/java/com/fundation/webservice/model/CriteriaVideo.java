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
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class CriteriaVideo extends CriteriaConvert{
    private String newFormat;
    //audio
    private String audioCodec;
    private int audioBit;
    private int audioChannel;
    private int audioRate;
    //video
    private String videoCodec;
    private String videoTag;
    private int videoBit;
    private int videoRate;

    public String getNewFormat() {
        return newFormat;
    }

    public void setNewFormat(String newFormat) {
        this.newFormat = newFormat;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public int getAudioBit() {
        return audioBit;
    }

    public void setAudioBit(int audioBit) {
        this.audioBit = audioBit;
    }

    public int getAudioChannel() {
        return audioChannel;
    }

    public void setAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }

    public int getAudioRate() {
        return audioRate;
    }

    public void setAudioRate(int audioRate) {
        this.audioRate = audioRate;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public String getVideoTag() {
        return videoTag;
    }

    public void setVideoTag(String videoTag) {
        this.videoTag = videoTag;
    }

    public int getVideoBit() {
        return videoBit;
    }

    public void setVideoBit(int videoBit) {
        this.videoBit = videoBit;
    }

    public int getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(int videoRate) {
        this.videoRate = videoRate;
    }
}
