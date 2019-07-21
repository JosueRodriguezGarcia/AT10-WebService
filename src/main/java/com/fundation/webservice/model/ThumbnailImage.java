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
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implements Thumbnail class an convert Method.
 *
 * @author Maday Alcalá Cuba, Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ThumbnailImage implements IConvert{
    /**
     * Obtains a 128x128 thumbnail of an image.
     *
     * @param criteriaConvert holds the required parameters to create the thumbnail:
     *     The source file path and the destination folder path.
     *     The output file name and the output file extension.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaThumbnailImage criteria = (CriteriaThumbnailImage) criteriaConvert;
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
            /*
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
             */
            process.waitFor();
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
