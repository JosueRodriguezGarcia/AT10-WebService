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

import java.io.File;

/**
 * Implements the model class File and the Criterion constructor.
 *
 * @author Maday Alcala Cuba, Limbert Alvaro Vargas Laura
 * @version 1.1
 */
public class VideoConvert implements IConvert {
    private CriteriaVideo criterion;

    public VideoConvert(CriteriaVideo criterion) {
        this.criterion = criterion;
    }

    public void convert() {
        try {
            File source = new File(criterion.getSrcPath());
            File target = new File(criterion.getDestPath());
            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(criterion.getAudioCodec());
            audio.setBitRate(criterion.getAudioBit());
            audio.setChannels(criterion.getAudioChannel());
            audio.setSamplingRate(criterion.getAudioRate());
            //Video Attributes
            VideoAttributes video = new VideoAttributes();
            video.setCodec(criterion.getVideoCodec());
            video.setTag(criterion.getVideoTag());
            video.setBitRate(new Integer(criterion.getVideoBit()));
            video.setFrameRate(new Integer(criterion.getVideoRate()));
            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(criterion.getNewFormat());
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
