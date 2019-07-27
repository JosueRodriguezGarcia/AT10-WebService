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
 * This class serves as unit test class for ConvertImage class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */
public class ConvertImageTest {
    @Test
    public void convert_image_image() {
        CriteriaImage criteriaImage = new CriteriaImage();
        criteriaImage.setSrcPath(Directories.RSRC_DIR.getDir() + "imageTest.jpg");
        criteriaImage.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaImage.setResolution("230x230");
        criteriaImage.setRotation(0);
        criteriaImage.setQuality(300);
        criteriaImage.setName("outputTest");
        criteriaImage.setExt("png");
        ConvertImage convertImage = new ConvertImage();
        convertImage.convert(criteriaImage);
        File output = new File(Directories.RSRC_DIR.getDir()
                + criteriaImage.getName() + "." + criteriaImage.getExt());
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
