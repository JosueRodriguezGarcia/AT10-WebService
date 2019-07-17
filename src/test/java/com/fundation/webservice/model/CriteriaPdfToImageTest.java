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
 * Implements CriteriaPdfToImageTest for testing CriteriaPdfToImage.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaPdfToImageTest {
    CriteriaPdfToImage criteriaTest = new CriteriaPdfToImage();

    @Before
    public void setUp() {
        criteriaTest.setSrcPath(Directories.RSRC_DIR.getDir() + "pdfTest.pdf");
        criteriaTest.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaTest.setDpi(new Integer("300"));
        criteriaTest.setFormatColor("RGB");
        criteriaTest.setName("output");
        criteriaTest.setExt(".png");
    }

    @Test
    public void getName() {
        assertEquals("output", criteriaTest.getName());
    }

    @Test
    public void getDpi() {
        assertEquals(300, criteriaTest.getDpi());
    }

    @Test
    public void getFormatColor() {
        assertEquals("RGB", criteriaTest.getFormatColor());
    }

    @Test
    public void setExt() {
        assertEquals(".png", criteriaTest.getExt());
    }
}