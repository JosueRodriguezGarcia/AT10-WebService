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
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
/**
  * Implements the model class File and the getter and setter´s methods
  *
  * @author Limbert Alvaro Vargas Laura
  * @version 1.0
 */
public class ConvertPptToImage {
    String input = "C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.pptx";
    String output = "C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.jpg";
    public ConvertPptToImage (String input, String output) {
        this.input = input;
        this.output = output;
    }
    public static void convertemf2png(String input,String output) {
        try {
            IMOperation img = new IMOperation();
            img.addImage();
            img.addImage();
            ConvertCmd convert = new ConvertCmd();
            convert.run(img,input,output);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}