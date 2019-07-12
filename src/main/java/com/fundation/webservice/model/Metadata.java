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

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implements an class that processes, displays and returns metadata info/files in several formats:
 * Currently XMP, JSON.
 *
 * @author Alejandro Sanchez
 * @version 1.0
 */
public class Metadata {
    CriteriaMetadata criteriaMetadata;


    public Metadata(CriteriaMetadata criteriaMetadata) {
        this.criteriaMetadata = criteriaMetadata;
    }

    public void showInfo() {
        String output = null;

        try {
            Process p = Runtime.getRuntime().exec("./3rdparty/exiftool/exiftool.exe -json " + criteriaMetadata.getSrcPath());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((output = stdInput.readLine()) != null) {
                System.out.println(output);
            }
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void xmp() {
        try {
            //String[] cli = { "cmd.exe", "/c", criteriaMetadata.getBIN_PATH() + "exiftool -X " + " > " + criteriaMetadata.getDestPath()};
            String[] cli = { "cmd.exe", "/c", "c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\3rdparty\\exiftool\\exiftool.exe -X c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\rsrc\\PS2TTT_intro.avi > c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\rsrc\\xmp.xmp"};
            Process process = new ProcessBuilder(cli).start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void json() {
        String s = null;
        try {
            String[] cli = { "cmd.exe", "/c", "c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\3rdparty\\exiftool\\exiftool.exe -json c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\rsrc\\PS2TTT_intro.avi > c:\\Users\\AlejandroSanchez\\Desktop\\alszla\\AT10-WebService\\rsrc\\json.json"};
            Process process = new ProcessBuilder(cli).start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println(System.getProperty("use.dir"));
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public HashMap<String, String> parseInfo() {
        HashMap<String, String> pairs = new HashMap<String, String>();
        return pairs;
    }
}
