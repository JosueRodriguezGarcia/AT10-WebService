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
 * Implements the criteria for create a Image Thumbnail.
 *
 * @author Maday Alcalá Cuba
 * @version 1.0
 */
public class CriteriaThumbnailImage extends CriteriaConvert {
    private String name;
    private String ext;

    /**
     * Method that returns the name of the output Thumbnail.
     *
     * @return The name of output thumbnail.
     */
    public String getName() {
        return name;
    }

	/**
     * Method that modifies the name of output thumbnail.
     *
     * @param name The name parameter define name of output thumbnail.
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
     * Method that returns the extentions of the output thumbnail.
     *
     * @return The extention for output thumbnail.
     */
    public String getExt() {
        return ext;
    }

	/**
     * Method that modifies the extetion of output thumbnail.
     *
     * @param time The exp parameter define the extetion of output thumbnail.
     */
    public void setExt(String ext) {
        this.ext = ext;
    }
}
