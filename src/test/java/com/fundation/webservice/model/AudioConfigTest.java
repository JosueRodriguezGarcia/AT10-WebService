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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class serves as unit test class for AudioConfig class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class AudioConfigTest {
    @Test
    public void getValueCaudioCodec() {
        assertEquals("libvorbis",AudioConfig.audioCodec.getValue());
    }

    @Test
    public void getValue_audioBitRate() {
        assertEquals("128000",AudioConfig.audioBitRate.getValue());
    }

    @Test
    public void getValue_audiochannel() {
        assertEquals("2",AudioConfig.audioChannel.getValue());
    }
}
