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

import com.spire.presentation.Presentation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class ConvertPptToImage {
    public static void main(String[] args) throws Exception {
        Presentation ppt = new Presentation();
        ppt.loadFromFile("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.ppt");
        for (int slides = 0; slides < ppt.getSlides().getCount(); slides++) {
            BufferedImage image = ppt.getSlides().get(slides).saveAsImage();
            String fileName = String.format("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER_IMG.png", slides);
            ImageIO.write(image, "PNG", new File(fileName));
        }
        ppt.dispose();
    }
}

