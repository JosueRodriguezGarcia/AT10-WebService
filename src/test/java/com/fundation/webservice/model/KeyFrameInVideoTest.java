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
 * This class serves as unit test class for keyframe in Video class.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class KeyFrameInVideoTest {
    CriteriaKeyFrameVideo criteriaKeyFrameVideo = new CriteriaKeyFrameVideo();

    @Test
    public void convert_video_videoWithKeyFrame() {
        criteriaKeyFrameVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaKeyFrameVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaKeyFrameVideo.setTime("1000");
        criteriaKeyFrameVideo.setName("keyframeInVideo");
        criteriaKeyFrameVideo.setExt("ogv");
        KeyFrameInVideo keyFrameInVideo = new KeyFrameInVideo();
        keyFrameInVideo.convert(criteriaKeyFrameVideo);
        File output = new File(Directories.RSRC_DIR.getDir() + criteriaKeyFrameVideo.getName()
                + "." + criteriaKeyFrameVideo.getExt());
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
