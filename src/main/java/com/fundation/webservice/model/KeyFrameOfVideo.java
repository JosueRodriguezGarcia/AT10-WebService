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
 * Implements the criteria for create a Keyframes.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class KeyFrameOfVideo {
    CriteriaKeyFrameVideo criteria;

    public KeyFrameOfVideo(CriteriaKeyFrameVideo criteria) {
        this.criteria = criteria;
    }

    /**
     * The convert method get the keyframe depending of the number of frame.
     */
    public void convert() {
        try {
            String cmd = "ffmpeg -i "
                + criteria.getSrcPath()
                + " -vf fps=1/"
                + criteria.getTime()
                + " "
                + criteria.getDestPath()
                + criteria.getName() + "%d."
                + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            do {
                line = in.readLine();
            } while (line != null);
            process.waitFor();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
