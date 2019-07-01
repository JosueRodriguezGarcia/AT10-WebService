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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author Josue Rodriguez
 * @version 1.0
 */
public class PdfToImage implements IConvert {
    private String pathOrigin;
    private String pathDestiny;
    private String nameOut;
    private int dpi;
    private String imageFormat;
    private String formatColor;

    public PdfToImage(String pathOrigin,
                      String pathDestiny,
                      String nameOut,
                      int dpi,
                      String imageFormat,
                      String formatColor) {
        this.pathOrigin = pathOrigin;
        this.pathDestiny = pathDestiny;
        this.nameOut = nameOut + "-";
        this.dpi = dpi;
        this.imageFormat = imageFormat;
        this.formatColor = formatColor;
    }

    public void convert() {
        try (final PDDocument document = PDDocument.load(new File(pathOrigin))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, dpi, ImageType.valueOf(formatColor));
                String fileName = pathDestiny + nameOut + page + imageFormat;
                ImageIOUtil.writeImage(bim, fileName, dpi);
            }
        } catch (IOException e) {
            System.err.println("Exception while trying to create pdf document - " + e);
        }
    }
}
