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
 * This class serves as unit test class for ConvertPdfToHtml class.
 *
 * @author Josue Rodriguez Garcia.
 * @version 1.0
 */
public class ConvertPdfToHtmlTest {
    @Test
    public void convert() {
        CriteriaConvert criteriaConvert = new CriteriaConvert();
        criteriaConvert.setSrcPath(Directories.RSRC_DIR.getDir() + "pdfTest.pdf");
        criteriaConvert.setDestPath(Directories.RSRC_DIR.getDir() + "pdfTest");
        ConvertPdfToHtml convertPdfToHtml = new ConvertPdfToHtml();
        convertPdfToHtml.convert(criteriaConvert);
        File output = new File(criteriaConvert.getDestPath() + ".html");
        boolean actual = output.exists();
        assertTrue(actual);
    }
}
