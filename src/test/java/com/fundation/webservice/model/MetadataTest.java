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

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class MetadataTest {
    Metadata metadata = new Metadata();

    @Test
    public void initCommandLine_stringResult() {
        metadata.initCommandLine();
        String actual = metadata.commandLine.get(0) + " " + metadata.commandLine.get(1) + " " + metadata.commandLine.get(2);
        assertEquals("cmd.exe /c " + metadata.USER_DIR + metadata.TOOLS_DIR + "exiftool/exiftool.exe" , actual);
    }

    @Test
    public void writeXmpFile_xmpFile() {
        File inputFile = new File(Directories.RSRC_DIR.getDir() + "PS2TTT_intro.mkv");
        metadata.writeXmpFile(inputFile);
        File outputFile = new File(Directories.RSRC_DIR.getDir() + inputFile.getName() + ".xmp");
        boolean actual = outputFile.exists();
        assertTrue(actual);
    }

    @Test
    public void writeJsonFile_jsonFile() {
        File inputFile = new File(Directories.RSRC_DIR.getDir() + "PS2TTT_intro.mkv");
        metadata.writeJsonFile(inputFile);
        File outputFile = new File(Directories.RSRC_DIR.getDir() + inputFile.getName() + ".json");
        boolean actual = outputFile.exists();
        assertTrue(actual);
    }
}
