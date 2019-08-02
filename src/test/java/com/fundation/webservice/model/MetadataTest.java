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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class serves as JUnit test class for the Metadata class from our model entity.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class MetadataTest {
    Metadata metadata = new Metadata();

    @Test
    public void initCommandLine_stringResult() {
        metadata.initCommandLine();
        String actual = metadata.commandLine.get(0) + " " + metadata.commandLine.get(1) + " "
                + metadata.commandLine.get(2);
        assertEquals("cmd.exe /c " + metadata.USER_DIR + metadata.TOOLS_DIR
                + "exiftool/exiftool.exe" , actual);
    }

    @Test
    public void initCommandLine_initialUnclearedString_stringResult() {
        String initialUnlearedString = "ThisStringShouldntBeHere";
        String anotherUnlearedString = "ThisStringShouldntBeHereEither";
        metadata.commandLine.add(initialUnlearedString);
        metadata.commandLine.add(anotherUnlearedString);
        metadata.initCommandLine();
        String actual = metadata.commandLine.get(0) + " " + metadata.commandLine.get(1) + " "
                + metadata.commandLine.get(2);
        assertEquals("cmd.exe /c " + metadata.USER_DIR + metadata.TOOLS_DIR
                + "exiftool/exiftool.exe" , actual);
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

    @Test
    public void writeXmpFile_null_xmpFile() {
        String nullInputPath = null;
        File inputFile = new File(nullInputPath);
        metadata.writeXmpFile(inputFile);
        File outputFile = new File(Directories.RSRC_DIR.getDir() + inputFile.getName() + ".xmp");
        boolean actual = outputFile.exists();
        assertTrue(actual);
    }

    @Test
    public void writeJsonFile_null_jsonFile() {
        String nullInputPath = null;
        File inputFile = new File(nullInputPath);
        metadata.writeJsonFile(inputFile);
        File outputFile = new File(Directories.RSRC_DIR.getDir() + inputFile.getName() + ".json");
        boolean actual = outputFile.exists();
        assertTrue(actual);
    }

    @Test
    public void parseToMap_map() throws Exception{
        String inputFilePath = Directories.RSRC_DIR.getDir() + "wild.wmv";
        File inputFile = new File(inputFilePath);
        Map map = metadata.parseToMap(inputFile);
        assertEquals(inputFile.getName(), map.get("File Name"));
    }

    @Test (expected = Exception.class)
    public void parseToMap_null_map() throws Exception{
        String inputFilePath = null;
        File inputFile = new File(inputFilePath);
        Map map = metadata.parseToMap(inputFile);
    }
}
