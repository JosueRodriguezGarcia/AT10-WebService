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

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * This class serves as Junit test class for ConvertAudio class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */
public class ConvertAudioTest {
    @Test
    public void convert_audioFile() {
        CriteriaAudio criteriaAudio = new CriteriaAudio();
        criteriaAudio.setSrcPath(Directories.RSRC_DIR.getDir() + "audioTest.ogg");
        criteriaAudio.setDestPath(Directories.RSRC_DIR.getDir() + "audioOutputTest.mp3");
        criteriaAudio.setAudioCodec("libmp3lame");
        criteriaAudio.setAudioBitRate(128000);
        criteriaAudio.setAudioChannel(2);
        criteriaAudio.setNewFormat("mp3");
        ConvertAudio convertAudio = new ConvertAudio();
        convertAudio.convert(criteriaAudio);
        File output = new File(criteriaAudio.getDestPath());
        boolean actual = output.exists();
        System.out.println(actual);
        assertTrue(actual);
    }
}