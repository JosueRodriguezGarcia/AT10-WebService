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
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class VideoResponseTest {

        String fileName = "name";
        String fileDownloadUri = "URI";
        String fileType = "video/codec";
        long size = 987654321;
        String newFormat = "format";
        String audioCodec = "libvorbis";
        String audioBitRate = "320000";
        String audioChannel = "2";
        String videoCodec = "mpeg4";
        String videoBitRate = "950000";
        String fps = "30";
        String metadata = "json";
        boolean thumbnail = true;
        boolean keyframes = true;
        String checksum = "1234567890123456789012";
        VideoResponse response = new VideoResponse(fileName, fileDownloadUri, fileType, size, newFormat, audioCodec,
            audioBitRate, audioChannel, videoCodec, videoBitRate, fps, metadata, thumbnail, keyframes, checksum);

    @Test
    public void getFileName() {
        assertEquals(fileName,response.getFileName());
    }

    @Test
    public void getFileDownloadUri() {
        assertEquals(fileDownloadUri, response.getFileDownloadUri());
    }

    @Test
    public void getType() {
        assertEquals(fileType,response.getFileType());
    }

    @Test
    public void getSize() {
        assertEquals(size,response.getSize());
    }

    @Test
    public void getNewFormat() {
        assertEquals(newFormat,response.getNewFormat());
    }

    @Test
    public void getAudioCodec() {
        assertEquals(audioCodec,response.getAudioCodec());
    }

    @Test
    public void getAudioBitRate() {
        assertEquals(audioBitRate,response.getAudioBitRate());
    }

    @Test
    public void getAudioChannel() {
        assertEquals(audioChannel,response.getAudioChannel());
    }

    @Test
    public void getVideoCodec() {
        assertEquals(videoCodec,response.getVideoCodec());
    }

    @Test
    public void getVideoBitRate() {
        assertEquals(videoBitRate,response.getVideoBitRate());
    }

    @Test
    public void getFps() {
        assertEquals(fps,response.getFps());
    }

    @Test
    public void getMetadata() {
        assertEquals(metadata,response.getMetadata());
    }

    @Test
    public void getThubnail() {
        assertEquals(thumbnail,response.getThumbnail());
    }

    @Test
    public void getKeyframes() {
        assertEquals(keyframes,response.getKeyframes());
    }

    @Test
    public void getChecksum() {
        assertEquals(checksum,response.getChecksum());
    }
}
