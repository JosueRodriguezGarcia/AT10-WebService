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
 * Implements CriteriaThumbnailVideoTest for testing CriteriaThumbnailVideo.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaThumbnailVideoTest {
    CriteriaThumbnailVideo criteriaTest = new CriteriaThumbnailVideo();

    @Before
    public void setUp() {
        criteriaTest.setTime("00:01:00");
        criteriaTest.setName("output");
        criteriaTest.setExt(".jpg");
    }

    @Test
    public void getTime() {
        String expected = "00:01:00";
        assertEquals(expected, criteriaTest.getTime());
    }

    @Test
    public void getName() {
        String expected = "output";
        assertEquals(expected, criteriaTest.getName());
    }

    @Test
    public void getExt() {
        String expected = ".jpg";
        assertEquals(expected, criteriaTest.getExt());
    }
}
