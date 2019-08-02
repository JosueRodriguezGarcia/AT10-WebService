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
 * Implements the CriteriaAudio class File and the getter and setter´s methods.
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class CriteriaAudio extends CriteriaConvert {
    private String newFormat;
    private String audioCodec;
    private int audioBitRate;
    private int audioChannel;

	/**
     * Method that returns new format of the output file.
     *
     * @return The new format for output file.
     */
    public String getNewFormat() {
        return newFormat;
    }

	/**
     * Method that modifies the format for output file.
     *
     * @param newFormat The newFormat parameter define the new format for output file.
     */
    public void setNewFormat(String newFormat) {
        this.newFormat = newFormat;
    }

	/**
     * Method that retun the audio codec for the convertion of audio.
     *
     * @return The codec for the convertion of audio.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

	/**
     * Method that modifies the codec for the convertion of audio.
     *
     * @param audioCodec The audioCodec parameter defines the codec for the audio transcoding.
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

	/**
     * Method that returns the bit rate of the output file.
     *
     * @return The number that represent the bit rate for output file.
     */
    public int getAudioBitRate() {
        return audioBitRate;
    }

	/**
     * Method that modifies the bitrate for output file.
     *
     * @param audioBitRate The audioBitRate parameter define the number of bitrate for output file.
     */
    public void setAudioBitRate(int audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

	/**
     * Method that returns number of channels for output file.
     *
     * @return The number of channels for output file.
     */
    public int getAudioChannel() {
        return audioChannel;
    }

	/**
     * Method that modifies number of channels for output file.
     *
     * @param audioChannel The audioChannel parameter define the number of channels for output file.
     */
    public void setAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }
}
