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
public class CriteriaKeyFrameVideo extends CriteriaConvert {
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

}
