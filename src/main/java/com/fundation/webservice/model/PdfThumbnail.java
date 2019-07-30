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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;

import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Provides a 128x128 thumbnail from the first page of a PDF document
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class PdfThumbnail implements IConvert{
    /**
     * Implements convert method as defined by IConvert Interface
     * This process is performed via Apache pdfbox and ImageMagick.
     *
     * @param criteriaConvert holds required parameters:
     *     Source file path, destination folder path, output file name and output file extension
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaPdfToImage criterion = (CriteriaPdfToImage)criteriaConvert;
        String magick = Directories.TOOLS_DIR.getDir() + Directories.IMAGEMAGIC_DIR.getDir() + "magick ";
        final int PAGE = 0;
        try (final PDDocument document = PDDocument.load(new File(criterion.getSrcPath()))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bim = pdfRenderer.renderImageWithDPI(PAGE, criterion.getDpi(), ImageType.valueOf(criterion.
                    getFormatColor()));
            String fileName = criterion.getDestPath() + criterion.getName() + PAGE + criterion.getExt();
            ImageIOUtil.writeImage(bim, fileName, criterion.getDpi());
            criterion.setSrcPath(fileName);
            String cmd = magick + criterion.getSrcPath() + " -thumbnail 128x128 " + criterion.getDestPath() + criterion.
                    getName() + criterion.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
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
