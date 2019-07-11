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
 * Implements the criteria for create a Thumbnail
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaThumbnailVideo extends CriteriaConvert {
    private String name;
    private String time;
    private String ext;

    //The getName method return the name of the output file
    public String getName() {
        return name;
    }

    //The setName method modify the name of the output file
    public void setName(String name) {
        this.name = name;
    }

    //The getTime Method return the time of the frame that we search.
    public String getTime() {
        return time;
    }

    //The setTime Method modify the time of the frame that we search
    public void setTime(String time) {
        this.time = time;
    }

    //The getExt Method return the extension of the output file
    public String getExt() {
        return ext;
    }

    //the setExt Method modify the extension od the output file
    public void setExt(String ext) {
        this.ext = ext;
    }
}
