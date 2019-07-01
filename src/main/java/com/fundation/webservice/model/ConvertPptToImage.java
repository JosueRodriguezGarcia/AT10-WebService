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
 * Implement the model class File when hardcode method for convert Power Point
 * file to another Image format (jpg, png, bmp).
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class ConvertPptToImage {
    public static void main(String[] args) throws Exception {
        Presentation ppt = new Presentation();
        ppt.loadFromFile("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.ppt");
        int proveSlides = ppt.getSlides().getCount();
        System.out.println(proveSlides);
        for (int slides = 0; slides < proveSlides; slides++) {
            BufferedImage image = ppt.getSlides().get(slides).saveAsImage();
            String pngFileName = String.format("C:\\Users\\LimbertVargas\\Desktop\\PruebaPptToImage\\PptToImagePNG\\OBSERVER_IMG"+slides+".png");
            ImageIO.write(image, "PNG", new File(pngFileName));
            String jpgFileName = String.format("C:\\Users\\LimbertVargas\\Desktop\\PruebaPptToImage\\PptToImageJPG\\OBSERVER_IMG"+slides+".jpg");
            ImageIO.write(image, "PNG", new File(jpgFileName));
            String bmpFileName = String.format("C:\\Users\\LimbertVargas\\Desktop\\PruebaPptToImage\\PptToImageBMP\\OBSERVER_IMG"+slides+".bmp");
            ImageIO.write(image, "PNG", new File(bmpFileName));
        }
        ppt.dispose();
    }
}

