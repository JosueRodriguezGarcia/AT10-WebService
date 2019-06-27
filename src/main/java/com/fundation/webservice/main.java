package com.fundation.webservice;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import java.io.File;

public class Main {
    public static void main(String arg[])
    {
        File input=new File("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.pptx");
        File output=new File("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.jpg");
        convertemf2png(input,output);
    }
    public static void convertemf2png(File input,File output)
    {
        try{
            IMOperation img=new IMOperation();
            img.addImage();
            img.addImage();
            ConvertCmd convert=new ConvertCmd();
            convert.run(img,new Object[]{input,output});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
