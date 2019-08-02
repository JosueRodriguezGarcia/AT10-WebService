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

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * This class serves as Junit test class for ConvertVideo class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class ConvertVideoTest {
    CriteriaVideo criteriaVideo = new CriteriaVideo();
    ConvertVideo convertVideo = new ConvertVideo();
    @Before
    public void setUP(){
        criteriaVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaVideo.setDestPath(Directories.RSRC_DIR.getDir() + "outVideoFile.mp4");
        criteriaVideo.setAudioCodec("libvorbis");
        criteriaVideo.setAudioBitRate(128000);
        criteriaVideo.setAudioChannel(2);
        criteriaVideo.setVideoCodec("mpeg4");
        criteriaVideo.setVideoBitRate(160000);
        criteriaVideo.setFps(30);
        convertVideo.convert(criteriaVideo);
    }
    @Test
    public void convert_videoFile(){
        File output = new File(criteriaVideo.getDestPath());
        boolean actual = output.exists();
        assertTrue(actual);
    }

    @Test
    public void convert_Size(){
        File output =  new File(criteriaVideo.getDestPath());
        long size = output.length();
        boolean actual = false;
        //Verify that Size > 0
        if (size >0){
            actual = true;
        }
        assertTrue(actual);
    }
}