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

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Implements Thumbnail class an convert Method.
 *
 * @author Josue Rodriguez,
 * @version 1.0
 */
public class ThumbnailVideo {
    CriteriaThumbnailVideo criteria;

    public ThumbnailVideo(CriteriaThumbnailVideo criteria) {
        this.criteria = criteria;
    }

    //The convert method capture a thumbnail of a frame specific.
    public void convert() {
        try {
            String cmd = Directories.TOOLS_DIR.getDir() + Directories.FFMPEG_DIR.getDir() + "ffmpeg -i "
                    + criteria.getSrcPath()
                    + " -ss "
                    + criteria.getTime()
                    + " -vframes 1 -s 128x128 "
                    + criteria.getDestPath()
                    + criteria.getName() + "."
                    + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            do {
                line = in.readLine();
            }
            while (line != null);
            process.waitFor();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
