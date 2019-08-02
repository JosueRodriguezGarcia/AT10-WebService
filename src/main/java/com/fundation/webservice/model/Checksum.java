/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.model;

import java.io.InputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Implements the Checksum class to extract this data for use in controller class.
 *
 * @author Limbert Vargas
 * @version 1.0
 */
public class Checksum {
    final static private int ARRAY_SIZE = 1024;
    final static private int BASE = 16;

    /**
     * Creates the checksum data using an algorithm.
     *
     * @param filename for be analized in the method.
     * @param algorithm for use as variable to access the MessageDigest class.
     * @return the checksum in complete.digest.
     * @throws Exception
     */
    public static byte[] createChecksum(String filename, String algorithm) throws Exception {
        InputStream inputStream =  new FileInputStream(filename);
        byte[] buffer = new byte[ARRAY_SIZE];
        MessageDigest complete = MessageDigest.getInstance(algorithm);
        int numRead;
        do {
            numRead = inputStream.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        inputStream.close();
        return complete.digest();
    }

    /**
     * Uses for return de checksum of the file.
     *
     * @param filename for be analized in the method.
     * @param algorithm for use as variable to access the MessageDigest class.
     * @return the result of the getChecksum method.
     * @throws Exception
     */
    public static String getChecksum(String filename, String algorithm) throws Exception {
        byte[] buffer = createChecksum(filename,algorithm);
        String result = "";
        for (int arrayIndex=0; arrayIndex < buffer.length; arrayIndex++) {
            result += Integer.toString( ( buffer[arrayIndex] & 0xff ) + 0x100, BASE).substring( 1 );
        }
        return result;
    }
}
