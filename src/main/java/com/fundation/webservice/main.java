package com.fundation.webservice;
import com.fundation.webservice.model.ConvertPptToImage;
import java.io.File;

public class main {
    public static void main(String arg[]) {
        File input = new File ("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.pptx");
        File output = new File ("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.jpg");
        ConvertPptToImage convert = new ConvertPptToImage(input,output);
        convert.converterformat();
    }
}