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
public class ConvertHtmlToDoc {      // To Do: implements "updated" IConvert
    public void convert(CriteriaConvert criteriaConvert) {
        try {
            Process process = Runtime.getRuntime().exec("3rdparty/" + "pandoc/" + "pandoc.exe -o " + criteriaConvert.getDestPath() + " " + criteriaConvert.getSrcPath());
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
