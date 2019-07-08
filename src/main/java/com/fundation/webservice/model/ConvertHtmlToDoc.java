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
 * Implements an Html To Doc converter class.
 *
 * @author Alejandro Sanchez
 * @version 1.0
 */
public class ConvertHtmlToDoc implements IConvert {
    CriteriaHtmlToDoc criteriaHtmlToDoc;

    public ConvertHtmlToDoc(CriteriaHtmlToDoc criteriaHtmlToDoc) {
        this.criteriaHtmlToDoc = criteriaHtmlToDoc;
    }

    public void convert() {
        String s = null;

        try {
            Process p = Runtime.getRuntime().exec("\\Users\\AlejandroSanchez\\Desktop\\alszla\\_i\\pandoc\\pandoc.exe -o " + criteriaHtmlToDoc.getDestPath() + " " + criteriaHtmlToDoc.getSrcPath());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = stdInput.readLine()) != null) { }
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
