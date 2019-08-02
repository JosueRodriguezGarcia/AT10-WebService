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
import static org.junit.Assert.assertFalse;

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

    @Test
    public void convert_video_criteriaWithNonExistentKeyframeTime_videoWithoutKeyFrame() {
        criteriaKeyFrameVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaKeyFrameVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaKeyFrameVideo.setTime("100000");
        criteriaKeyFrameVideo.setName("keyframeInVideo");
        criteriaKeyFrameVideo.setExt("ogv");
        KeyFrameInVideo keyFrameInVideo = new KeyFrameInVideo();
        keyFrameInVideo.convert(criteriaKeyFrameVideo);
        File output = new File(Directories.RSRC_DIR.getDir() + criteriaKeyFrameVideo.getName()
                + "." + criteriaKeyFrameVideo.getExt());
        boolean actual = output.exists();
        assertTrue(actual);
    }

    @Test (expected = Exception.class)
    public void convert_video_criteriaWithNonExistentVideo_noVideo() {
        criteriaKeyFrameVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "nonExistentVideo.ogv");
        criteriaKeyFrameVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaKeyFrameVideo.setTime("100");
        criteriaKeyFrameVideo.setName("keyframeInVideo");
        criteriaKeyFrameVideo.setExt("ogv");
        KeyFrameInVideo keyFrameInVideo = new KeyFrameInVideo();
        keyFrameInVideo.convert(criteriaKeyFrameVideo);
    }

    @Test (expected = Exception.class)
    public void KeyFrameInVideo_blankCriteria_resultIllegalArgumentException() {
        CriteriaKeyFrameVideo blankCriteria = new CriteriaKeyFrameVideo();
        KeyFrameInVideo keyFrameInVideo = new KeyFrameInVideo();
        keyFrameInVideo.convert(blankCriteria);
    }
}
