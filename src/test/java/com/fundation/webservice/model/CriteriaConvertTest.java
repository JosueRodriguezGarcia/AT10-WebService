package com.fundation.webservice.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CriteriaConvertTest {
    CriteriaConvert criteriaConvert = new CriteriaConvert();

    @Before
    public void setUp(){
        criteriaConvert.setSrcPath(Directories.RSRC_DIR.getDir());
        criteriaConvert.setDestPath(Directories.RSRC_DIR.getDir());
    }
    @Test
    public void getSrcPath() {
        assertEquals("rsrc/",criteriaConvert.getSrcPath());
    }

    @Test
    public void getDestPath() {
        assertEquals("rsrc/",criteriaConvert.getDestPath());
    }
}