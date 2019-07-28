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
 * Handles document conversion from ppt or pptx format to PDF format
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class ConvertPPTtoPdf  extends Run implements IConvert {
    private final String OFFICETOPDF_DIR = Directories.OFFICETOPDF_DIR.getDir();

    /**
     * Implements convert(CriteriaConvert) from IConvert interface.
     * Conversion is performed via OfficeToPDF tool.
     *
     * @param criteriaConvert hold the source file path.
     */
    public void convert(CriteriaConvert criteriaConvert){
        initCommandLine();
        commandLine.add("/hidden");
        commandLine.add(criteriaConvert.getSrcPath());
        commandLine.add(criteriaConvert.getDestPath());
        run();
    }

    /**
     * Initializates the list structure that stores the command line string to be passed to the
     * run() method.
     */
    public void initCommandLine(){
        super.initCommandLine();
        commandLine.add(USER_DIR + TOOLS_DIR + OFFICETOPDF_DIR + "officetopdf.exe");
    }
}
