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
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertWordToImageTest {
    @Test
    public void convert_docx_jpg() {
        CriteriaPdfToImage criteriaPdfToImage = new CriteriaPdfToImage();
        criteriaPdfToImage.setSrcPath(Directories.RSRC_DIR.getDir() + "BugLifeCycleFAQ.docx");
        criteriaPdfToImage.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaPdfToImage.setName("outputImage");
        criteriaPdfToImage.setExt(".jpg");
        criteriaPdfToImage.setDpi(300);
        criteriaPdfToImage.setFormatColor("RGB");
        ConvertWordToImage convertWordToImage = new ConvertWordToImage();
        convertWordToImage.convert(criteriaPdfToImage);
        String actual = Directories.RSRC_DIR.getDir() + criteriaPdfToImage.getName() + "0" + criteriaPdfToImage.getExt();
        File outputFile = new File(actual);
        assertTrue(outputFile.exists());
    }

    public String filenameWithoutExtension(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }
}
