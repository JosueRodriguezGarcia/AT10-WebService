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

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

/**
 * Performs document conversion from PDF format to docx formats.
 * This class is limited to text extraction only.
 *
 * @author Josue Rodriguez, Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ConvertPdfToWord implements IConvert{
    /**
     * Implements convert(CriteriaConvert) from IConvert interface.
     * Conversion is performed via Apache poi utility.
     *
     * @param criteriaConvert holds the source and destination file paths.
     */
    public void convert(CriteriaConvert criteriaConvert) {
        XWPFDocument doc = new XWPFDocument();
        String pdf = criteriaConvert.getSrcPath();
        try {
            PdfReader reader = new PdfReader(pdf);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            for (int index = 1; index <= reader.getNumberOfPages(); index++) {
                TextExtractionStrategy strategy = parser.processContent(index, new SimpleTextExtractionStrategy());
                String text = strategy.getResultantText();
                XWPFParagraph p = doc.createParagraph();
                XWPFRun run = p.createRun();
                run.setText(text);
                run.addBreak(BreakType.PAGE);
            }
            FileOutputStream out = new FileOutputStream(criteriaConvert.getDestPath());
            doc.write(out);
            out.close();
            reader.close();
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
