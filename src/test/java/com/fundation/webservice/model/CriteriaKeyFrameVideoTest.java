package com.fundation.webservice.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CriteriaKeyFrameVideoTest {

    CriteriaKeyFrameVideo criteriaTest = new CriteriaKeyFrameVideo();
    @Before
    public void setUp(){
        criteriaTest.setSrcPath(Directories.RSRC_DIR.getDir()+"videoTest.ogg");
        criteriaTest.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaTest.setFrames("1000");
        criteriaTest.setName("output");
        criteriaTest.setExt("mp4");
    }
    @Test
    public void getFrames() {
        assertEquals("1000",criteriaTest.getFrames());
    }

    @Test
    public void getName() {
        assertEquals("output",criteriaTest.getName());
    }

    @Test
    public void getExt() {
        assertEquals("mp4",criteriaTest.getExt());
    }
}