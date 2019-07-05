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
    private CriteriaVideo criterion;
    public VideoConvert(CriteriaVideo criterion){
        this.criterion = criterion;
    }

    public void convert() {
        try {
            File source = new File(criterion.getSrcPath());
            File target = new File(criterion.getDestPath());
            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(criterion.getaCodec());
            audio.setBitRate(criterion.getaBit());
            audio.setChannels(criterion.getaChannel());
            audio.setSamplingRate(criterion.getaRate());
            //Video Attributes
            VideoAttributes video = new VideoAttributes();
            video.setCodec(criterion.getvCodec());
            video.setTag(criterion.getvTag());
            video.setBitRate(new Integer(criterion.getvBit()));
            video.setFrameRate(new Integer(criterion.getvRate()));
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