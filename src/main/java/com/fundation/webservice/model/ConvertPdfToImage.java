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
 * @author Josue Rodriguez
 * @version 1.0
 */
public class ConvertPdfToImage implements IConvert{
    CriteriaPdfToImage criterion;
    ConvertPdfToImage(CriteriaPdfToImage criterio){
            this.criterion = criterio;
    }
    public void convert() {
        try (final PDDocument document = PDDocument.load(new File(criterion.getSrcPath()))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, criterion.getDpi(), ImageType.valueOf(criterion.getFormatColor()));
                String fileName = criterion.getDestPath() + criterion.getName() + page + criterion.getExt();
                ImageIOUtil.writeImage(bim, fileName, criterion.getDpi());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
