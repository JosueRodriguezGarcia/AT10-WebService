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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class implements the CriteriaPptxToImageTest class File and getter method for testing CriteriaPptxToImage class.
 *
 * @author Limbert Alvaro Vargas Laura.
 * @version 1.0
 */
public class CriteriaPptxToImageTest {

    @Test
    public void getExt() {
        CriteriaPptxToImage criteriaPptxToImage = new CriteriaPptxToImage();
        criteriaPptxToImage.setExt("jpg");
        assertEquals("jpg",criteriaPptxToImage.getExt());
    }
}
