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
 * @author Josue Rodriguez
 * @version 1.0
 */
public class CriteriaConvert {
    private String srcPath;
    private String destPath;
    private String nameOutputFile;
    private String extOutputFile;

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    /**
     * Method that returns the name of the output file.
     *
     * @return The a String with the mane of output file
     */
    public String getNameOutputFile() {
        return nameOutputFile;
    }

    /**
     * Method that modifies the name of the output file.
     *
     * @param nameOutputFile The name parameter define name of output file.
     */
    public void setNameOutputFile(String nameOutputFile) {
        this.nameOutputFile = nameOutputFile;
    }

    /**
     * Method that return the extension of the output file.
     *
     * @return The extension of output file.
     */
    public String getExtOutputFile() {
        return extOutputFile;
    }

    /**
     * Method that Modifies the extension od the output file.
     *
     * @param extOutputFile The ext parameter define extension of output file.
     */
    public void setExtOutputFile(String extOutputFile) {
        this.extOutputFile = extOutputFile;
    }
}
