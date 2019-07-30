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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class implements the CriteriaVideoTest class File and the getters methods for testing CriteriaVideo class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class CriteriaVideoTest {
    CriteriaVideo criteriaVideo = new CriteriaVideo();

    @Before
    public void setUp(){
        criteriaVideo.setNewFormat("mp4");
        criteriaVideo.setAudioCodec("libmp3lame");
        criteriaVideo.setAudioBitRate(128000);
        criteriaVideo.setAudioChannel(2);
        criteriaVideo.setVideoCodec("mpeg4");
        criteriaVideo.setVideoBitRate(160000);
        criteriaVideo.setFps(60);
    }

    @Test
    public void getNewFormat() {
        assertEquals("mp4",criteriaVideo.getNewFormat());
    }

    @Test
    public void getAudioCodec() {
        assertEquals("libmp3lame",criteriaVideo.getAudioCodec());
    }

    @Test
    public void getAudioBitRate() {
        assertEquals(128000,criteriaVideo.getAudioBitRate());
    }

    @Test
    public void getAudioChannel() {
        assertEquals(2,criteriaVideo.getAudioChannel());
    }

    @Test
    public void getVideoCodec() {
        assertEquals("mpeg4",criteriaVideo.getVideoCodec());
    }

    @Test
    public void getVideoBitRate() {
        assertEquals(160000,criteriaVideo.getVideoBitRate());
    }

    @Test
    public void getFps() {
        assertEquals(60,criteriaVideo.getFps());
    }
}
