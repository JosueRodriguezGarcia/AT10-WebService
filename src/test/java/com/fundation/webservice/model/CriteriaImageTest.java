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

import static org.junit.Assert.*;

/**
 * This class implements the CriteriaImageTest class File and the getter methods for testing CriteriaImage class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class CriteriaImageTest {
    CriteriaImage criteriaImage = new CriteriaImage();

    @Before
    public void setUp(){
        criteriaImage.setName("nameTest");
        criteriaImage.setRotation(90);
        criteriaImage.setExt("png");
        criteriaImage.setResolution("90x100");
        criteriaImage.setQuality(125);
    }

    @Test
    public void getName() {
        assertEquals("nameTest",criteriaImage.getName());
    }

    @Test
    public void getRotation() {
        assertEquals(90,criteriaImage.getRotation());
    }

    @Test
    public void getExt() {
        assertEquals("png",criteriaImage.getExt());
    }

    @Test
    public void getResolution() {
        assertEquals("90x100",criteriaImage.getResolution());
    }

    @Test
    public void getQuality() {
        assertEquals(125,criteriaImage.getQuality());
    }
}
