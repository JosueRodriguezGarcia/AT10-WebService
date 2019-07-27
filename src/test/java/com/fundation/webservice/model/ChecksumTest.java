package com.fundation.webservice.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ChecksumTest {

    @Test
    public void getChecksum() throws Exception {
        String filename = Directories.RSRC_DIR.getDir() + "audioTest.ogg";
        String algorithm = "MD5";
        String actual = Checksum.getChecksum(filename, algorithm);
        String expected = "3dd9fcc94ecabd86270ef1b152d3e7c8";
        assertEquals(expected,actual);
    }
}