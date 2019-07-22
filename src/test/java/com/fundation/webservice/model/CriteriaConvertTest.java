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
 * Implements CriteriaConvertTest for testing CriteriaConvert.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaConvertTest {
    CriteriaConvert criteriaConvert = new CriteriaConvert();

    @Before
    public void setUp() {
        criteriaConvert.setSrcPath(Directories.RSRC_DIR.getDir());
        criteriaConvert.setDestPath(Directories.RSRC_DIR.getDir());
    }

    @Test
    public void getSrcPath() {
        assertEquals("rsrc/", criteriaConvert.getSrcPath());
    }

    @Test
    public void getDestPath() {
        assertEquals("rsrc/", criteriaConvert.getDestPath());
    }
}
