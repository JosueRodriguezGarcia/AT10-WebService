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
 *
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaPdfToImage extends CriteriaConvert{
    private String name;
    private int dpi;
    private String ext;
    private String formatColor;

	/**
     * Method that returns the name for each output image.
     *
     * @return The name for each output image.
     */
    public String getName() {
        return name;
    }

	/**
     * Method that modifies the name for each output image.
     *
     * @param name The name parameter define name for each outfile image.
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
     * Method that returns the PDI to get the image.
     *
     * @return The PDI to get the image.
     */
    public int getDpi() {
        return dpi;
    }

	/**
     * Method that modifies the values of PDI for image.
     *
     * @param dpi The dpi parameter define the PDI for get the image.
     */
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

	/**
     * Method that returns the extentions of the output file.
     *
     * @return The extention for output file.
     */
    public String getExt() {
        return ext;
    }

	/**
     * Method that modifies the extetion of output file.
     *
     * @param time The exp parameter define the extetion of output file.
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

	/**
     * Method that returns the format color of outfile file.
     *
     * @return The the format color of output file
     */
    public String getFormatColor() {
        return formatColor;
    }

	/**
     * Method that modifies the format color of output file.
     *
     * @param time The formatColor parameter define color format of output file.
     */
    public void setFormatColor(String formatColor) {
        this.formatColor = formatColor;
    }
}
