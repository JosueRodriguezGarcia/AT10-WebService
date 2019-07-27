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
 * This class serves as unit test class for checksum class.
 *
 * @author Josue Rodriguez Garcia
 * @version 1.0
 */
public class ChecksumTest {
    @Test
    public void getChecksum() throws Exception {
        String filename = Directories.RSRC_DIR.getDir() + "audioTest.ogg";
        String algorithm = "MD5";
        String actual = Checksum.getChecksum(filename, algorithm);
        String expected = "3dd9fcc94ecabd86270ef1b152d3e7c8";
        assertEquals(expected, actual);
    }
}
