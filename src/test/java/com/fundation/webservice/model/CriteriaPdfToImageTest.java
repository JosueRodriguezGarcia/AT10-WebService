package com.fundation.webservice.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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