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
 * Performs video file transcoding based on the parameters specified through a CriteriaConvert class.
 *
 * @author Maday Alcala Cuba, Limbert Alvaro Vargas Laura, Alejandro Sánchez Luizaga
 * @version 1.1
 */
public class ConvertVideo implements IConvert{
    /**
     * Implmentation of convert(CriteriaConvert) method specified through IConvert interface
     * Conversion is performed through ffmpeg utility
     *
     * @param criteriaConvert holds a very specialized set of transcoding parameters that are going to be fed to
     *     ffmpeg utility.
     *     Source file path,
     *     Destination file path,
     *     Audio codec,
     *     Audio bitrate,
     *     Number of audio channels,
     *     Audio sampling rate,
     *     Video codec,
     *     Video tag,
     *     Video bitrate,
     *     Video framerate,
     *     Video container.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaVideo criterion = (CriteriaVideo) criteriaConvert;
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
