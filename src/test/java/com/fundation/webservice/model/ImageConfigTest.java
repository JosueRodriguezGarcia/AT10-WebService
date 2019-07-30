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
 * This class serves as unit test class for ImageConfig class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class ImageConfigTest {

    @Test
    public void getValue_resolution() {
        assertEquals("640x320", ImageConfig.resolution.getValue());
    }

    @Test
    public void getValue_rotation() {
        assertEquals("0", ImageConfig.rotation.getValue());
    }

    @Test
    public void getValue_quality() {
        assertEquals("75", ImageConfig.quality.getValue());
    }

    @Test
    public void getValue_metadata() {
        assertEquals("", ImageConfig.metadata.getValue());
    }

    @Test
    public void getValue_thumbnail() {
        assertEquals("false", ImageConfig.thumbnail.getValue());
    }
}