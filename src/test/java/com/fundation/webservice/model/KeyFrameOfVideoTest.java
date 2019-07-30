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

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

/**
 * This class implements the KeyFrameOfVideoTest class File and the methods for testing KeyFrameOfVideo class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class KeyFrameOfVideoTest {
    CriteriaKeyFrameVideo criteriaKeyFrameVideo = new CriteriaKeyFrameVideo();

    @Test
    public void convert() {
        criteriaKeyFrameVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.mp4");
        criteriaKeyFrameVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaKeyFrameVideo.setName("keyFrameOfVideo");
        criteriaKeyFrameVideo.setTime("500");
        criteriaKeyFrameVideo.setExt("mp4");
        KeyFrameOfVideo keyFrameOfVideo = new KeyFrameOfVideo();
        keyFrameOfVideo.convert(criteriaKeyFrameVideo);
        File output = new File(Directories.RSRC_DIR.getDir() + criteriaKeyFrameVideo.getName() + "." +
                criteriaKeyFrameVideo.getExt());
        boolean actual = output.exists();
        assertFalse(actual);
    }
}
