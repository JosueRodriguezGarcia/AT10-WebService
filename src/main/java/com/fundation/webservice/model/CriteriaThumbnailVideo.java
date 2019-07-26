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
 * Implements the criteria for create a Thumbnail.
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaThumbnailVideo extends CriteriaConvert {
    private String name;
    private String time;
    private String ext;

	/**
     * Method that return the name of the output file.
     *
     * @return The name of output thumbnail.
     */
    public String getName() {
        return name;
    }

	/**
     * Method that modifies the name of the output thumbnail.
     *
     * @param name The name parameter define name of outfile thumbnail.
     */
    public void setName(String name) {
        this.name = name;
    }
	
	/**
     * Method that return the time of capture the thumbnail.
     *
     * @return The time of output thumbnail.
     */
    public String getTime() {
        return time;
    }

	/**
     * Method that modifies the time of capture the thumbnail.
     *
     * @param time The time parameter define name of outfile thumbnail.
     */
    public void setTime(String time) {
        this.time = time;
    }

	/**
     * Method that return the extension of the output thumbnail.
     *
     * @return The ext of output thumbnail.
     */
    public String getExt() {
        return ext;
    }

	/**
     * Method that modifies the extension od the output thumbnail.
     *
     * @param ext The ext parameter define extention for outfile thumbnail.
     */
    public void setExt(String ext) {
        this.ext = ext;
    }
}
