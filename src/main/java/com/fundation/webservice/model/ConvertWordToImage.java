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
public class ConvertWordToImage extends Run implements IConvert2{
    //ConvertWordToPdf convertWordToPdf;
    //ConvertPdfToImage convertPdfToImage;

    public ConvertWordToImage() {
        //this.convertWordToPdf = convertWordToPdf;
        //this.convertPdfToImage = convertPdfToImage;
    }

    public void convert(File file) {

    }

    public void convert(CriteriaConvert criteriaConvert) {
        File inputFile = new File(criteriaConvert.getSrcPath());
        ConvertWordToPdf convertWordToPdf = new ConvertWordToPdf();
        convertWordToPdf.convert(inputFile);
        String intermediateName = filenameWithoutExtension(inputFile);
        criteriaConvert.setSrcPath(USER_DIR + Directories.RSRC_DIR.getDir() + intermediateName + ".pdf");
        ConvertPdfToImage convertPdfToImage = new ConvertPdfToImage((CriteriaPdfToImage) criteriaConvert);
        convertPdfToImage.convert();
    }

    public String filenameWithoutExtension(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }

}
