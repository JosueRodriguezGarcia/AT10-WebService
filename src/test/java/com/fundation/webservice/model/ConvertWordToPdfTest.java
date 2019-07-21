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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.File;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertWordToPdfTest {
    @Test
    public void convert_docx_pdf() {
        CriteriaConvert criteria = new CriteriaConvert();
        criteria.setSrcPath(Directories.RSRC_DIR.getDir() + "BugLifeCycleFAQ.docx");
        ConvertWordToPdf convertWordToPdf = new ConvertWordToPdf();
        convertWordToPdf.convert(criteria);
        File input = new File(criteria.getSrcPath());
        String actual = Directories.RSRC_DIR.getDir() + filenameWithoutExtension(input) + ".pdf";
        File output = new File(actual);
        assertTrue(output.exists());
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
