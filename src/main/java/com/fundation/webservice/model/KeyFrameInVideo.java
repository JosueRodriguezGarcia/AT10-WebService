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
 * Implements KeyframeVideo class an convert Method.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class KeyFrameInVideo {

    /**
     * Converts method add a keyframe for every certain amount of frame.
     */
    public void convert(CriteriaKeyFrameVideo criteriaKeyFrameVideo) {

        try {
            String cmd = "ffmpeg -y -i " + criteriaKeyFrameVideo.getSrcPath() + " -vcodec libx264 -x264-params keyint="
                    + criteriaKeyFrameVideo.getTime() + ":scenecut=0 -acodec copy " + criteriaKeyFrameVideo.getDestPath()
                    + criteriaKeyFrameVideo.getName() + "."
                    + criteriaKeyFrameVideo.getExt();
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
