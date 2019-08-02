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
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implements the criteria for create a Keyframes.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class KeyFrameOfVideo implements IConvert{
    /**
     * Converts method get the keyframe depending of the number of frame.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaKeyFrameVideo criteria = (CriteriaKeyFrameVideo)criteriaConvert;
        try {
            String cmd = Directories.TOOLS_DIR.getDir() + Directories.FFMPEG_DIR.getDir() + "ffmpeg -y -i "
                + criteria.getSrcPath() + " -vf fps=1/" + criteria.getTime() + " " + criteria.getDestPath()
                + criteria.getName() + "%d." + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            do {
                line = in.readLine();
            } while (line != null);
            process.waitFor();
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
