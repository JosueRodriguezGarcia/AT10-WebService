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

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

/**
 * Implements an class that processes and writes metadata info into in several metadata formats:
 * Currently supports XMP, JSON and Java Map structure.
 *
 * @author Alejandro Sanchez
 * @version 1.0
 */
public class Metadata extends Run{
    private final String EXIFTOOL_DIR = Directories.EXIFTOOL_DIR.getDir();

    /**
     * This method writes a xmp file containing the param file metadata info returned by exiftool in XMP format.
     * The xmp file is stored in the same directory where the param file is located.
     *
     * @param file The handle (provided by a File object) to the file that is going to be read by exiftool.
     */
    public void writeXmpFile(File file) {
        initCommandLine();
        commandLine.add(file.getAbsolutePath());
        commandLine.add("-X");
        commandLine.add(">");
        commandLine.add(file.getParent() + "/" + file.getName() + ".xmp");
        run();
    }

    /**
     * This method writes a json file containing the param file metadata info returned by exiftool in JSON format.
     * The json file is stored in the same directory where the param file is located.
     *
     * @param file The handle (provided by a File object) to the file that is going to be read by exiftool.
     */
    public void writeJsonFile(File file) {
        initCommandLine();
        commandLine.add(file.getAbsolutePath());
        commandLine.add("-json");
        commandLine.add(">");
        commandLine.add(file.getParent() + "/" + file.getName() + ".json");
        run();
    }

    /**
     * This method parses the metadata info returned by exiftool and stores it as a Map structure containing
     * keyname/value pairs.
     *
     * @param file The handle (provided by a File object) to the param file that is going to be read by exiftool.
     *
     * @return a Map structure containing the pairs provided by exiftool
     */
    public Map<String, String> parseToMap(File file) {
        Map<String, String> result = new HashMap<String, String>();
        try{
            initCommandLine();
            commandLine.add(file.getAbsolutePath());
            Process process = new ProcessBuilder(commandLine).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = input.readLine()) != null) {
                String[] pair = line.split(":",2);
                if ((pair != null) && (pair.length == 2)) {
                    result.put(pair[0].trim(), pair[1].trim());
                }
            }
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
        return result;
    }

    /**
     * Safe initialization of the list structure that stores the command line string to be passed to the
     * run() method.
     */
    public void initCommandLine() {
        super.initCommandLine();
        commandLine.add(USER_DIR + TOOLS_DIR + EXIFTOOL_DIR + "exiftool.exe");
    }

    /**
     * This method is used to determine the name of the output file.
     * The output file is going to be named after the original file, but the extension will be replaced according
     * to the metadata format that the client asks for.
     *
     * @param file The handle (provided by a File object) to the param file that is going to be read by exiftool.
     * @return the name of a file without its extension.
     */
    public String fileNameWithoutExtension(File file) {
        String fileNameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            fileNameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return fileNameWithoutExtension;
    }
}
