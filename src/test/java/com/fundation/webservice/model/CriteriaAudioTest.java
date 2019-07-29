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

import static org.junit.Assert.assertEquals;

/**
 * This class implements the CriteriaAudioTest class File and the getter methods for testing CriteriaAudio class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class CriteriaAudioTest {
    CriteriaAudio criteriaAudio = new CriteriaAudio();

    /**
     * This method permit create similar objects that is use before they can run.
     */
    @Before
    public void setUp (){
        criteriaAudio.setNewFormat("mp3");
        criteriaAudio.setAudioCodec("libmp3lame");
        criteriaAudio.setAudioBitRate(128000);
        criteriaAudio.setAudioChannel(2);
    }

    /**
     * This Test returns expected mp3 format when enter actual status in getNewFormat.
     */
    @Test
    public void getNewFormat() {
        assertEquals("mp3",criteriaAudio.getNewFormat());
    }

    /**
     * This Test returns expected "libmp3lame" codec when enter actual codec in getAudioCodec.
     */
    @Test
    public void getAudioCodec() {
        assertEquals("libmp3lame",criteriaAudio.getAudioCodec());
    }

    /**
     * This Test returns expected 128000 audio bit rate when enter actual bit rate un getAudioBitRate.
     */
    @Test
    public void getAudioBitRate() {
        assertEquals(128000,criteriaAudio.getAudioBitRate());
    }

    /**
     * This Test returns expected "2" audio channel when enter actual audio channel in getAudioChannel.
     */
    @Test
    public void getAudioChannel() {
        assertEquals(2,criteriaAudio.getAudioChannel());
    }
}
