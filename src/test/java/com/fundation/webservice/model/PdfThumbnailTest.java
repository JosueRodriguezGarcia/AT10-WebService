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
 * This class serves as Junit test class for PdfThumbnail class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */
public class PdfThumbnailTest {
    @Test
    public void convert() {
        CriteriaPdfToImage criteriaConvert = new CriteriaPdfToImage();
        criteriaConvert.setSrcPath(Directories.RSRC_DIR.getDir() + "pdfTest.pdf");
        criteriaConvert.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaConvert.setDpi(300);
        criteriaConvert.setFormatColor("RGB");
        criteriaConvert.setName("test");
        criteriaConvert.setExt("png");
        PdfThumbnail pdfThumbnail = new PdfThumbnail();
        pdfThumbnail.convert(criteriaConvert);
        File output = new File(Directories.RSRC_DIR.getDir() + "test.png");
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
