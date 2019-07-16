package com.fundation.webservice.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CriteriaVideoTest {

    CriteriaVideo testVideo = new CriteriaVideo();

    @Test
    public void getNewFormat() {
        String expected = "C;\\josue\\test.mp4";
        testVideo.setSrcPath("C;\\josue\\test.mp4");
        assertEquals(expected, testVideo.getSrcPath());
    }

    @Test
    public void setNewFormat() {
    }

    @Test
    public void getaCodec() {
        String expected = "C;\\josue\\";
        testVideo.setDestPath("C;\\josue\\");
        assertEquals(expected, testVideo.getDestPath());
    }

    @Test
    public void setaCodec() {
    }

    @Test
    public void getaBit() {
    }

    @Test
    public void setaBit() {
    }

    @Test
    public void getaChannel() {
    }

    @Test
    public void setaChannel() {
    }

    @Test
    public void getaRate() {
    }

    @Test
    public void setaRate() {
    }

    @Test
    public void getvCodec() {
    }

    @Test
    public void setvCodec() {
    }

    @Test
    public void getvTag() {
    }

    @Test
    public void setvTag() {
    }

    @Test
    public void getvBit() {
    }

    @Test
    public void setvBit() {
    }

    @Test
    public void getvRate() {
    }

    @Test
    public void setvRate() {
    }
}