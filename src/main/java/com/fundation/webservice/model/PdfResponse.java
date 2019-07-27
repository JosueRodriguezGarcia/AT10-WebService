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
    // Getters needed so Spring Boot Framework can return object as JSON
    public String getFileName() {
        return fileName;
    }

    public String getPdfDownloadUri() {
        return pdfDownloadUri;
    }

    public String getRefactorType() {
        return refactorType;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getDpi() {
        return dpi;
    }

    public String getExt() {
        return ext;
    }

    public String getFormatColor() {
        return formatColor;
    }
}
