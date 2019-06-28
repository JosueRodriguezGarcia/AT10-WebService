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
import org.im4java.process.ProcessStarter;
import java.io.File;
/**
  * Implements the model class File and the getter and setter´s methods
  *
  * @author Limbert Alvaro Vargas Laura
  * @version 1.0
 */
public class ConvertPptToImage {
    File input;
    File output;
    public ConvertPptToImage (File input, File output) {
        this.input = input;
        this.output = output;
    }
    public void converterformat() {
        String myPath = "PATH\\TO\\ImageMagick";
        ProcessStarter.setGlobalSearchPath(myPath);
        try {
            IMOperation image = new IMOperation();
            image.addImage();
            ConvertCmd convert = new ConvertCmd();
            convert.run(image,input,output);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

