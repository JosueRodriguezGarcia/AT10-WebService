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
 * Enum list that holds all path strings for various key directories.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public enum Directories {
    RSRC_DIR("rsrc/"),
    TOOLS_DIR("3rdparty/"),
    EXIFTOOL_DIR("exiftool/"),
    FFMPEG_DIR("ffmpeg/bin/"),
    IMAGEMAGIC_DIR("ImageMagic/"),
    PANDOC_DIR("pandoc/"),
    POPPLER_DIR("poppler/bin/");

    private String dir;

    Directories(String dir) {
        this.dir = dir;
    }

    /**
     * This is a getter method that returns the string paired to its corresponding enum element.
     *
     * @return the string paired to a enum element
     */
    public String getDir() {
        return dir;
    }
}
