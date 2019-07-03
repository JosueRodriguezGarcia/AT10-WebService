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

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

import java.util.logging.Logger;
import java.io.File;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Maday Alcala Cuba
 * @version 1.0
 */
public class VideoConvert implements IConvert {
    private String inputFile;
    private String outputFile;
    private String newFormat;

    //audio
    private String aCodec;
    private int aBit;
    private int aChannel;
    private int aRate;

    //video
    private String vCodec;
    private String vTag;
    private int vBit;
    private int vRate;

    public VideoConvert(String inputFile, String outputFile, String newFormat,
                        String aCodec, int aBit, int aChannel, int aRate,
                        String vCodec, String vTag, int vBit, int vRate) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.newFormat = newFormat;

        //audio
        this.aCodec = aCodec;
        this.aBit = aBit;
        this.aChannel = aChannel;
        this.aRate = aRate;

        //video
        this.vCodec = vCodec;
        this.vBit = vBit;
        this.vTag = vTag;
        this.vRate = vRate;

    }

    public void convert() {
        try {
            File source = new File(inputFile);
            File target = new File(outputFile);

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(aCodec);
            audio.setBitRate(aBit);
            audio.setChannels(aChannel);
            audio.setSamplingRate(aRate);

            //Video Attributes
            VideoAttributes video = new VideoAttributes();
            video.setCodec(vCodec);
            video.setTag(vTag);
            video.setBitRate(new Integer(vBit));
            video.setFrameRate(new Integer(vRate));

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(newFormat);
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}