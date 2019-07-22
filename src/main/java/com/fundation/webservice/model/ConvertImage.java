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
 * @author Maday Alcala Cuba
 * @version 1.0
 */
public class ConvertImage {
    CriteriaImage criteria;

    public ConvertImage(CriteriaImage criteria) {
        this.criteria = criteria;
    }

    /**
     * This method converts an image from one format to another.
     */
    public void convert() {
        String magick = "3rdparty/ImageMagic/magick ";
        try {
            String cmd = magick
                    + criteria.getSrcPath()
                    + " -resize " + criteria.getResolution()
                    + " -rotate " + criteria.getRotation()
                    + " -quality " + criteria.getQuality() + " "
                    + criteria.getDestPath()
                    + criteria.getName() + "."
                    + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
