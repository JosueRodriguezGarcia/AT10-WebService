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
public class Metadata {
    private final String USER_DIR = System.getProperty("user.dir") + "/";
    private final String TOOLS_DIR = "3rdparty/";
    private final String EXIFTOOL_DIR = "exiftool/";

    // Writes an xmp file containing the file metadata.
    // The xmp file is stored in the same directory where the file is located
    public void xmp(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        try {
            String[] cli = { "cmd.exe",
                    "/c",
                    USER_DIR + TOOLS_DIR + EXIFTOOL_DIR + "exiftool.exe -X " +
                            file.getAbsolutePath() +
                            " > " +
                            file.getParent() + "/" + filenameWithoutExtension + ".xmp"};
            Process process = new ProcessBuilder(cli).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
    }

    // Writes a json file containing the file metadata.
    // The xmp file is stored in the same directory where the file is located
    public void json(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        try {
            String[] cli = { "cmd.exe",
                "/c",
                USER_DIR + TOOLS_DIR + EXIFTOOL_DIR + "exiftool.exe -json " +
                    file.getAbsolutePath() +
                    " > " +
                    file.getParent() + "/" + filenameWithoutExtension + ".json"};
            Process process = new ProcessBuilder(cli).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
    }

    // Returns a Map structure containing the metadata info as key/value pairs
    public Map<String, String> parse(File file) {
        Map<String, String> result = new HashMap<String, String>();
        try{
            String[] cli = { "cmd.exe",
                    "/c",
                    USER_DIR + TOOLS_DIR + EXIFTOOL_DIR + "exiftool.exe " + file.getAbsolutePath()};
            Process process = new ProcessBuilder(cli).start();
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
}
