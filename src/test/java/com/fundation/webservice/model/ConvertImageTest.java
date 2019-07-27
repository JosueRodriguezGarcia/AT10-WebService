package com.fundation.webservice.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ConvertImageTest {

    @Test
    public void convert_image_image() {
        CriteriaImage criteriaImage = new CriteriaImage();
        criteriaImage.setSrcPath(Directories.RSRC_DIR.getDir() + "imageTest.jpg");
        criteriaImage.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaImage.setResolution("230x230");
        criteriaImage.setRotation(0);
        criteriaImage.setQuality(300);
        criteriaImage.setName("outputTest");
        criteriaImage.setExt("png");
        ConvertImage convertImage = new ConvertImage();
        convertImage.convert(criteriaImage);
        File output = new File(Directories.RSRC_DIR.getDir()
                + criteriaImage.getName() + "." + criteriaImage.getExt() );
        boolean actual =  output.exists();
        assertTrue(actual);
    }
}