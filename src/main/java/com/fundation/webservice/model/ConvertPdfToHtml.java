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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Performs document conversion from PDF format to HTML format.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertPdfToHtml implements IConvert{
    /**
     * Implements convert(CriteriaConvert) from IConvert interface.
     * Conversion is performed via pdftohtml from poppler-utils.
     *
     * @param criteriaConvert
     */
    public void convert(CriteriaConvert criteriaConvert) {
        CriteriaThumbnailImage criteria = (CriteriaThumbnailImage) criteriaConvert;
        try {
            Process process = Runtime.getRuntime().exec(Directories.TOOLS_DIR.getDir() + Directories.POPPLER_DIR.getDir() + "pdftohtml.exe -c -noframes " + criteria.getSrcPath() + " " + criteria.getDestPath() + criteria.getName() + criteria.getExt());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();
            stdInput.close();
        }
       catch (IOException e) {
           e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
