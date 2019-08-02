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

import static org.junit.Assert.assertEquals;

/**
 * This class serves as unit test class for OfficeToPdfConfig enum.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class OfficeToPdfConfigTest {
    @Test
    public void getValue_metadata() {
        assertEquals("", OfficeToPdfConfig.metadata.getValue());
    }

    @Test
    public void getValue_thumbnail() {
        assertEquals("false", OfficeToPdfConfig.thumbnail.getValue());
    }
}
