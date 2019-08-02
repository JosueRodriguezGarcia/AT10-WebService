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
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaPptxToImage extends CriteriaConvert {
    private String ext;

	/**
     * Method that returns the extensions of the output file.
     *
     * @return The extention for output file.
     */
    public String getExt() {
        return ext;
    }

	/**
     * Method that modifies the extetion of output file.
     *
     * @param ext The ext parameter define the extension of output file.
     */
    public void setExt(String ext) {
        this.ext = ext;
    }
}
