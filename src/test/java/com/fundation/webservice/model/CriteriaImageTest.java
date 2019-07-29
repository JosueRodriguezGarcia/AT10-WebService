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

    /**
     * This method permit create similar objects that is use before they can run.
     */
    @Before
    public void setUp(){
        criteriaImage.setName("nameTest");
        criteriaImage.setRotation(90);
        criteriaImage.setExt("png");
        criteriaImage.setResolution("90x100");
        criteriaImage.setQuality(125);
    }

    /**
     * This method returns expected "nameTest" name when enter actual name in getName.
     */
    @Test
    public void getName() {
        assertEquals("nameTest",criteriaImage.getName());
    }

    /**
     * This method returns expected 90 degrees of rotation when enter actual value in getRotation.
     */
    @Test
    public void getRotation() {
        assertEquals(90,criteriaImage.getRotation());
    }

    /**
     * This method returns expected "png" format extension when enter actual format extension in getExt.
     */
    @Test
    public void getExt() {
        assertEquals("png",criteriaImage.getExt());
    }

    /**
     * This method returns "90x100" image resolution when enter actual image resolution in getResolution.
     */
    @Test
    public void getResolution() {
        assertEquals("90x100",criteriaImage.getResolution());
    }

    /**
     * This method returns 125 pixels of image quality when enter actual image quality in getQuality.
     */
    @Test
    public void getQuality() {
        assertEquals(125,criteriaImage.getQuality());
    }
}
