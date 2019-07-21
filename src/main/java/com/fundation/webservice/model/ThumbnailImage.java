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
import java.io.InputStreamReader;

/**
 * Implements Thumbnail class an convert Method.
 *
 * @author Maday Alcalá Cuba
 * @version 1.0
 */
public class ThumbnailImage {
    CriteriaThumbnailImage criteria;

    public ThumbnailImage(CriteriaThumbnailImage criteria) {
        this.criteria = criteria;
    }

    /**
     * Obtains a thumbnail of an image.
     */
    public void convert() {
        String magick = "3rdparty/ImageMagic/magick ";
        try {
            String cmd = magick
                    + criteria.getSrcPath()
                    + " -thumbnail 128x128 "
                    + criteria.getDestPath()
                    + criteria.getName() + "."
                    + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
