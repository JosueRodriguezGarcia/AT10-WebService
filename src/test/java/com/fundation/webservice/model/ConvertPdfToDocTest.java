package com.fundation.webservice.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ConvertPdfToDocTest {

    @Test
    public void convert_pdf_doc() {
        CriteriaPdfToDoc criteriaPdfToDoc = new CriteriaPdfToDoc();
        criteriaPdfToDoc.setSrcPath(Directories.RSRC_DIR.getDir() + "pdfTest.pdf");
        criteriaPdfToDoc.setDestPath(Directories.RSRC_DIR.getDir() + "pdfTest.docx");
        ConvertPdfToDoc convertPdfToDoc = new ConvertPdfToDoc();
        convertPdfToDoc.convert(criteriaPdfToDoc);
        File output = new File(criteriaPdfToDoc.getDestPath());
        boolean actual = output.exists();
        assertTrue(actual);
    }
}