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
 * Implements CriteriaKeyFrameTest for testing CriteriaKeyFrameVideo.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaKeyFrameVideoTest {
    CriteriaKeyFrameVideo criteriaTest = new CriteriaKeyFrameVideo();

    @Before
    public void setUp() {
        criteriaTest.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogg");
        criteriaTest.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaTest.setTime("60");
        criteriaTest.setName("output");
        criteriaTest.setExt("mp4");
    }

    @Test
    public void getFrames() {
        assertEquals("60", criteriaTest.getTime());
    }

    @Test
    public void getName() {
        assertEquals("output", criteriaTest.getName());
    }

    @Test
    public void getExt() {
        assertEquals("mp4", criteriaTest.getExt());
    }
}
