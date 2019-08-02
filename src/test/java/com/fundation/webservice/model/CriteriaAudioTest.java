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

    @Before
    public void setUp (){
        criteriaAudio.setNewFormat("mp3");
        criteriaAudio.setAudioCodec("libmp3lame");
        criteriaAudio.setAudioBitRate(128000);
        criteriaAudio.setAudioChannel(2);
    }

    @Test
    public void getNewFormat() {
        assertEquals("mp3",criteriaAudio.getNewFormat());
    }

    @Test
    public void getAudioCodec() {
        assertEquals("libmp3lame",criteriaAudio.getAudioCodec());
    }

    @Test
    public void getAudioBitRate() {
        assertEquals(128000,criteriaAudio.getAudioBitRate());
    }

    @Test
    public void getAudioChannel() {
        assertEquals(2,criteriaAudio.getAudioChannel());
    }
}
