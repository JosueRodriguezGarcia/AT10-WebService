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

/**
 * Implements a type of response to an /uploadPdf request.
 *
 * @author Maday Alcala Cuba, Jesus Menacho
 * @version 1.0
 */
public class PdfResponse extends Response{
    private String fileName;
    private String pdfDownloadUri;
    private String refactorType;
    private long size;
    private String name;
    private String dpi;
    private String ext;
    private String formatColor;

    public PdfResponse(String fileName, String pdfDownloadUri, String pdfType, long size,
                       String name, String dpi, String ext, String formatColor) {
        this.fileName = fileName;
        this.pdfDownloadUri = pdfDownloadUri;
        this.refactorType = pdfType;
        this.size = size;
        this.name = name;
        this.dpi = dpi;
        this.ext = ext;
        this.formatColor = formatColor;
    }

    /**
     * Method that return the name of outputFile
     *
     * @return name the output File.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Method that return URL of output file for Download.
     *
     * @return Uri the output File.
     */
    public String getPdfDownloadUri() {
        return pdfDownloadUri;
    }

    /**
     * Method that return the Type of data of the output file.
     *
     * @return Type the output File.
     */
    public String getRefactorType() {
        return refactorType;
    }

    /**
     * Method that return the size of output File.
     *
     * @return Size the output File.
     */
    public long getSize() {
        return size;
    }

    /**
     * Method that return the name of output File.
     *
     * @return name of output File.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that return the dpi of file.
     *
     * @return dpi of file.
     */
    public String getDpi() {
        return dpi;
    }

    /**
     * Method that return the extention of file.
     *
     * @return extention of file.
     */
    public String getExt() {
        return ext;
    }

    /**
     * Method that return the format color of file.
     *
     * @return format color of file.
     */
    public String getFormatColor() {
        return formatColor;
    }
}
