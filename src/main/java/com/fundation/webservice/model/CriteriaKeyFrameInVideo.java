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
public class CriteriaKeyFrameInVideo extends CriteriaConvert {
    private String frames;
    private String name;
    private String ext;

    /**
     * Method that returns the interval of frame for each keyframe.
     *
     * @return The number of frames for each interval of keyframe
     */
    public String getFrames() {
        return frames;
    }

    /**
     * Method that modifies the interval of frame for each keyframe.
     *
     * @param frames The frames parameter define the number of frames for each interval of keyframe in output file.
     */
    public void setFrames(String frames) {
        this.frames = frames;
    }

    /**
     * Method that returns the name of the output file.
     *
     * @return The a String with the mane of output file
     */
    public String getName() {
        return name;
    }

    /**
     * Method that modifies the name of the output file.
     *
     * @param name The name parameter define name of output file.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that return the extension of the output file.
     *
     * @return The extension of output file.
     */
    public String getExt() {
        return ext;
    }

    /**
     * Method that Modifies the extension od the output file.
     *
     * @param ext The ext parameter define extension of output file.
     */
    public void setExt(String ext) {
        this.ext = ext;
    }
}
