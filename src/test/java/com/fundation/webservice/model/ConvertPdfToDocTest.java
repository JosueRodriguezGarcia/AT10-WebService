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

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * This class serves as unit test class for ConvertPdfToDoc class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class ConvertPdfToDocTest {
    @Test
    public void convert_pdf_doc() {
        CriteriaPdfToHtml criteriaPdfToDoc = new CriteriaPdfToHtml();
        criteriaPdfToDoc.setSrcPath(Directories.RSRC_DIR.getDir() + "pdfTest.pdf");
        criteriaPdfToDoc.setDestPath(Directories.RSRC_DIR.getDir() + "pdfTest");
        ConvertPdfToDoc convertPdfToDoc = new ConvertPdfToDoc();
        convertPdfToDoc.convert(criteriaPdfToDoc);
        File output = new File(criteriaPdfToDoc.getDestPath());
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
