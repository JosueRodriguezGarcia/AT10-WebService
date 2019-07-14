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

/**
 * Implements the criteria for create a Keyframes.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaKeyFrameVideo extends CriteriaConvert{
    private String frames;
    private String name;
    private String ext;

    //Returns the interval of frame for each keyframe.
    public String getFrames() {
        return frames;
    }

    //Modifies the interval of frame for each keyframe.
    public void setFrames(String frames) {
        this.frames = frames;
    }

    //Returns the name of the output file.
    public String getName() {
        return name;
    }

    //Modifies the name of the output file.
    public void setName(String name) {
        this.name = name;
    }

    //Returns the extension of the output file.
    public String getExt() {
        return ext;
    }

    //Modifies the extension od the output file.
    public void setExt(String ext) {
        this.ext = ext;
    }
}
