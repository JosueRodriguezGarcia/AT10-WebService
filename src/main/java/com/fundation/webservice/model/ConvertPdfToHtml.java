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
 * Implements a Pdf To Html converter class.
 *
 * @author Alejandro Sanchez
 * @version 1.0
 */
public class ConvertPdfToHtml implements IConvert{
    CriteriaPdfToHtml criteriaPdfToHtml;

    public ConvertPdfToHtml(CriteriaPdfToHtml criteriaConvert) {
        this.criteriaPdfToHtml = criteriaConvert;
    }

    public void convert() {
        try {
            Process pdfToHtml = Runtime.getRuntime().exec("./3rdparty/poppler/bin/pdftohtml.exe -c -noframes " + criteriaPdfToHtml.getSrcPath() + " " + criteriaPdfToHtml.getDestPath());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(pdfToHtml.getInputStream()));
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
