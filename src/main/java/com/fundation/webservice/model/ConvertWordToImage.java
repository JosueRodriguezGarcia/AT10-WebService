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

import java.io.File;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertWordToImage extends Run{
    /**
     * Wrapper method that integrates WordToPdf's convert(CriteriaConvert) and PdfToImage's convert()
     *
     * @param criteriaConvert
     */
    public void convert(CriteriaConvert criteriaConvert) {
        File inputFile = new File(criteriaConvert.getSrcPath());
        ConvertWordToPdf convertWordToPdf = new ConvertWordToPdf();
        convertWordToPdf.convert(criteriaConvert);
        String intermediateName = filenameWithoutExtension(inputFile);
        criteriaConvert.setSrcPath(USER_DIR + Directories.RSRC_DIR.getDir() + intermediateName + ".pdf");
        ConvertPdfToImage convertPdfToImage = new ConvertPdfToImage();
        convertPdfToImage.convert((CriteriaPdfToImage) criteriaConvert);
    }

    /**
     * Safe initialization of the list structure that stores the command line string to be passed to the
     * run() method.
     */
    public String filenameWithoutExtension(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }
}
