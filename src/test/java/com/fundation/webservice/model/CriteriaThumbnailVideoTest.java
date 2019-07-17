package com.fundation.webservice.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CriteriaThumbnailVideoTest {

    CriteriaThumbnailVideo criteriaTest = new CriteriaThumbnailVideo();
    @Before
    public void setUp(){
        criteriaTest.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaTest.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaTest.setTime("00:01:00");
        criteriaTest.setName("output");
        criteriaTest.setExt(".jpg");
    }

    @Test
    public void getSrcPath_true() {
        String expected = "rsrc/videoTest.ogv";
        assertEquals(expected, criteriaTest.getSrcPath());
    }

    @Test
    public void getDestPath_true() {
        String expected = "rsrc/";
        assertEquals(expected, criteriaTest.getDestPath());
    }

    public void getTime_true() {
        String expected = "00:01:00";
        assertEquals(expected, criteriaTest.getTime());
    }

    @Test
    public void getName_true() {
        String expected = "output";
        assertEquals(expected, criteriaTest.getName());
    }

    @Test
    public void getExt_true() {
        String expected = ".jpg";
        assertEquals(expected, criteriaTest.getExt());
    }

/*    @Test
    public void convert_video() {
        ThumbnailVideo test = new ThumbnailVideo(criteriaTest);
        test.convert();
        String f = criteriaTest.getDestPath()+criteriaTest.getName()+criteriaTest.getExt();
        File output = new File(f);
        assertTrue(output.exists());
    }*/
}