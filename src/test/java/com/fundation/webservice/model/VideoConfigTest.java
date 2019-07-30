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

import static org.junit.Assert.assertEquals;

/**
 * This class serves as unit test class for VideoConfig class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class VideoConfigTest {
    @Test
    public void getValue_audioCodec() {
        assertEquals("libvorbis", VideoConfig.audioCodec.getValue());
    }

    @Test
    public void getValue_audioBitRate() {
        assertEquals("128000", VideoConfig.audioBitRate.getValue());
    }

    @Test
    public void getValue_audioChannel() {
        assertEquals("2", VideoConfig.audioChannel.getValue());
    }

    @Test
    public void getValue_videoCodec() {
        assertEquals("mpeg4", VideoConfig.videoCodec.getValue());
    }

    @Test
    public void getValue_videoBitRate() {
        assertEquals("320000", VideoConfig.videoBitRate.getValue());
    }

    @Test
    public void getValue_fps() {
        assertEquals("30", VideoConfig.fps.getValue());
    }

    @Test
    public void getValue_metadata() {
        assertEquals("", VideoConfig.metadata.getValue());
    }

    @Test
    public void getValue_thumbnail() {
        assertEquals("false", VideoConfig.thumbnail.getValue());
    }

    @Test
    public void getValue_keyframes() {
        assertEquals("false", VideoConfig.keyframes.getValue());
    }
}
