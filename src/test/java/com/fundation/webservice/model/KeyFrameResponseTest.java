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
public class KeyFrameResponseTest {

    @Test
    public void getFileName() {
        String fileName = "video.mp4";
        String URI = "http://localhost/download/video.mp4";
        KeyFrameResponse test = new KeyFrameResponse(fileName,URI);
        assertEquals(fileName,test.getFileName());
    }

    @Test
    public void getFileDownloadUri() {
        String fileName = "video.mp4";
        String URI = "http://localhost/download/video.mp4";
        KeyFrameResponse test = new KeyFrameResponse(fileName,URI);
        assertEquals(URI,test.getFileDownloadUri());
    }

    @Test (expected = Exception.class)
    public void keyFrameResponse_null_resultException() {
        String fileName = null;
        String URI = null;
        KeyFrameResponse test = new KeyFrameResponse(fileName,URI);
        test.getFileName();
    }
}
