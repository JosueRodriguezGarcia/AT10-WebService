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
 * This class serves as Junit test class for ConvertPptxToImage class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */
public class ConvertPptxToImageTest {
    @Test
    public void convert_ppt_image() {
        CriteriaPptxToImage criteriaPptxtoImage = new CriteriaPptxToImage();
        criteriaPptxtoImage.setSrcPath(Directories.RSRC_DIR.getDir() + "pptTest.pptx");
        criteriaPptxtoImage.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaPptxtoImage.setExt("jpg");
        ConvertPptxToImage convertPptxToImage = new ConvertPptxToImage();
        convertPptxToImage.convert(criteriaPptxtoImage);
        File output = new File(criteriaPptxtoImage.getDestPath() + "0.jpg");
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
