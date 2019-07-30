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
 * This class serves as Junit test class for ThumbnailImage class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class ThumbnailImageTest {

    @Test
    public void convert() {
        CriteriaThumbnailImage criteriaThumbnailImage = new CriteriaThumbnailImage();
        criteriaThumbnailImage.setSrcPath(Directories.RSRC_DIR.getDir() + "imageTest.jpg");
        criteriaThumbnailImage.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaThumbnailImage.setName("imageThumbnail");
        criteriaThumbnailImage.setExt("png");
        ThumbnailImage thumbnailImage = new ThumbnailImage();
        thumbnailImage.convert(criteriaThumbnailImage);
        File outputFile = new File(criteriaThumbnailImage.getDestPath() + criteriaThumbnailImage.getName() +
                "." + criteriaThumbnailImage.getExt());
        Boolean actual = outputFile.exists();
        assertTrue(actual);
    }
}
