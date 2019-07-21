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
 * This class performs video file transcoding based on the parameters specified through a CriteriaConvert class.
 *
 * @author Limbert Alvaro Vargas Laura, Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertAudio implements IConvert{
    /**
     * Implmentation of convert(CriteriaConvert) method specified through IConvert interface
     *
     * @param criteriaConvert holds the requried parameters to perform audio transcoding
     *     Source file path,
     *     Destination file path,
     *     Audio codec,
     *     Audio bitrate,
     *     Number of audio channels,
     *     Audio sampling rate,
     *     Audio container.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaAudio criterion = (CriteriaAudio)criteriaConvert;
        try {
            File source = new File(criterion.getSrcPath());
            File target = new File(criterion.getDestPath());

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(criterion.getAudioCodec());
            audio.setBitRate(criterion.getAudioBit());
            audio.setChannels(criterion.getAudioChannel());
            audio.setSamplingRate(criterion.getAudioRate());

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(criterion.getNewFormat());
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
