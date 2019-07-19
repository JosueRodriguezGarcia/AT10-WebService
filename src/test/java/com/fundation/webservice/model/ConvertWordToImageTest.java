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
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertWordToImageTest {
    @Test
    public void convert_docx_jpg() {
        CriteriaPdfToImage pruebaImagen = new CriteriaPdfToImage();
        pruebaImagen.setSrcPath(Directories.RSRC_DIR.getDir() + "BugLifeCycleFAQ.docx");
        pruebaImagen.setDestPath(Directories.RSRC_DIR.getDir());
        pruebaImagen.setName("pepino");
        pruebaImagen.setExt(".jpg");
        pruebaImagen.setDpi(300);
        pruebaImagen.setFormatColor("RGB");
        IConvert2 convertWordToImage = new ConvertWordToImage();
        convertWordToImage.convert(pruebaImagen);
        String actual = Directories.RSRC_DIR.getDir() + pruebaImagen.getName() + "0" + pruebaImagen.getExt();
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
