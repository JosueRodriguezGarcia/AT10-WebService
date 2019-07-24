package com.fundation.webservice.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConvertPPTtoPdf {
    private static final String PATH_TO_EXE = "C:\\Users\\LimbertVargas\\Desktop\\AT10-WebService\\3rdparty\\officetopdf\\OfficeToPDF.exe";
    private static final String PATH_TO_TEMPLATE = "C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.ppt";
    private static final String PATH_TO_OUTPUT = "C:\\Users\\LimbertVargas\\Desktop\\pruebaOutput.pdf";
    public static void main(String[] args) throws IOException, InterruptedException {
        Process process;
        process = new ProcessBuilder(PATH_TO_EXE, PATH_TO_TEMPLATE, PATH_TO_OUTPUT).start();
        process.waitFor();

        System.out.println("Result of Office processing: " + process.exitValue());
        File file = new File(PATH_TO_OUTPUT);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        System.out.println(fileContent.length);
    }
}