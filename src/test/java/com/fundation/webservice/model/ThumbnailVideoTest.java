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

import static org.junit.Assert.*;

/**
 * This class serves as Junit test class for ThumbnailVideo class.
 *
 * @author Josue Rodriguez Garcia, Alejandro Sánchez Luizaga
 * @version 1.0
 */

public class ThumbnailVideoTest {
    @Test
    public void convert() {
        CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
        criteriaThumbnailVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaThumbnailVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaThumbnailVideo.setTime("30");
        criteriaThumbnailVideo.setName("thumbnail");
        criteriaThumbnailVideo.setExt("bmp");
        ThumbnailVideo thumbnailVideo = new ThumbnailVideo();
        thumbnailVideo.convert(criteriaThumbnailVideo);
        File outputFile = new File(criteriaThumbnailVideo.getDestPath() + criteriaThumbnailVideo.getName() + "."
                + criteriaThumbnailVideo.getExt());
        Boolean actual =  outputFile.exists();
        assertTrue(actual);
    }

    @Test (expected = Exception.class)
    public void convert_blankCriteria_resultException() {
        CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
        ThumbnailVideo thumbnailVideo = new ThumbnailVideo();
        thumbnailVideo.convert(criteriaThumbnailVideo);
    }

    @Test
    public void convert_invalidTimeValue_resultException() {
        CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
        criteriaThumbnailVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaThumbnailVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaThumbnailVideo.setTime("30000000000000000000000000000000000000000000000");
        criteriaThumbnailVideo.setName("thumbnail");
        criteriaThumbnailVideo.setExt("bmp");
        ThumbnailVideo thumbnailVideo = new ThumbnailVideo();
        thumbnailVideo.convert(criteriaThumbnailVideo);
        File outputFile = new File(criteriaThumbnailVideo.getDestPath() + criteriaThumbnailVideo.getName() + "."
                + criteriaThumbnailVideo.getExt());
        Boolean actual =  outputFile.exists();
        assertTrue(actual);
    }
}
