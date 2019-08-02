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

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Performs document conversion from PPT format to image formats.
 *
 * @author Josue Rodriguez, Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertPptxToImage implements IConvert{
    /**
     * Implements convert(CriteriaConvert) from IConvert interface.
     * Conversion is performed via Apache poi utility.
     *
     * @param criteriaConvert holds source and destination file paths and output file extension.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        final int POS_X = 0;
        final int POS_Y = 0;

        CriteriaPptxToImage criteria = (CriteriaPptxToImage) criteriaConvert;
        try {
            File file = new File(criteria.getSrcPath());
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
            Dimension pgsize = ppt.getPageSize();
            List<XSLFSlide> slide = ppt.getSlides();
            BufferedImage img = null;
            for (int index = 0; index < slide.size(); index++) {
                img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(POS_X, POS_Y, pgsize.width, pgsize.height));
                //render
                slide.get(index).draw(graphics);
                FileOutputStream out = new FileOutputStream(criteria.getDestPath() + index + "." + criteria.getExt());
                javax.imageio.ImageIO.write(img, criteria.getExt(), out);
                ppt.write(out);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
