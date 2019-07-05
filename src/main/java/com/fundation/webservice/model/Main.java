package com.fundation.webservice.model;

public class Main {
    public static void main(String[] args) {
        /*
        CriteriaConvert prueba = new CriteriaPdfToWord();
        prueba.setSrcPath("C:\\_pg\\factors.pdf");
        prueba.setDestPath("C:\\_pg\\");
        prueba.setExt(".png");
        prueba.setDpi(300);
        prueba.setFormatColor("RGB");
        prueba.setName("josue-");
        PdfToImage hola = new PdfToWord(prueba);
        hola.convert();
        */
        
        /*
        CriteriaPdfToHtml prueba = new CriteriaPdfToHtml();
        prueba.setSrcPath("C:\\_pg\\tuli.pdf");
        prueba.setDestPath("C:\\_pg\\tuli.html");
        PdfToHtml hola = new PdfToHtml(prueba);
        hola.convert();
        */
        
        CriteriaHtmlToDoc prueba = new CriteriaHtmlToDoc();
        prueba.setSrcPath("C:\\_pg\\tuli.html");
        prueba.setDestPath("C:\\_pg\\tuli.docx");
        HtmlToDoc hola = new HtmlToDoc(prueba);
        hola.convert();
    }
}

