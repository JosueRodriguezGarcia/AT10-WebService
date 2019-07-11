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

import java.io.File;

/**
 * Implements the criterion and AudioConvert class.
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class AudioConvert implements IConvert{
    private CriteriaAudio criteria;

    /**
     *
     * @param criteria is defined in the constructor for use in convert class.
     */
    public AudioConvert(CriteriaAudio criteria) {
        this.criteria = criteria;
    }

    /**
     * Class convert is takes care of change to the different audio formats.
     */
    public void convert() {
        try {
            File source = new File(criteria.getSrcPath());
            File target = new File(criteria.getDestPath());
            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(criteria.getaCodec());
            audio.setBitRate(criteria.getaBit());
            audio.setChannels(criteria.getaChannel());
            audio.setSamplingRate(criteria.getaRate());
            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(criteria.getNewFormat());
            attrs.setAudioAttributes(audio);
            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
