package com.fundation.webservice.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ConvertPPTtoPdfTest {

    @Test
    public void convert_ppt_pdf() {
        CriteriaConvert criteria = new CriteriaConvert();
        criteria.setSrcPath(Directories.RSRC_DIR.getDir() + "pptTest.ppt");
        criteria.setDestPath(Directories.RSRC_DIR.getDir() + "pptTest.pdf");
        ConvertPPTtoPdf convertPPTtoPdf = new ConvertPPTtoPdf();
        convertPPTtoPdf.convert(criteria);
        File input = new File(criteria.getSrcPath());
        String actual = Directories.RSRC_DIR.getDir() + filenameWithoutExtension(input) + ".pdf";
        File output = new File(actual);
        assertTrue(output.exists());
    }

    public String filenameWithoutExtension(File file){
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if(dotPosition != -1){
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }
}
