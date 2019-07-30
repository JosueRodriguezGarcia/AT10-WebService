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
 * This class implements the CriteriaThumbnailImageTest class File and the getter methods for testing CriteriaThumbnailImage class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class CriteriaThumbnailImageTest {
    CriteriaThumbnailImage thumbnailImage = new CriteriaThumbnailImage();

    /**
     * This method permit create similar objects that is use before they can run.
     */
    @Before
    public void setUp(){
        thumbnailImage.setName("nameTest");
        thumbnailImage.setExt("png");
    }

    /**
     * This method returns expected "nameTest" file name when enter actual file name in getName.
     */
    @Test
    public void getName() {
        assertEquals("nameTest",thumbnailImage.getName());
    }

    /**
     * This method returns expected "png" image extension when enter actual extension in getExt.
     */
    @Test
    public void getExt() {
        assertEquals("png",thumbnailImage.getExt());
    }
}
