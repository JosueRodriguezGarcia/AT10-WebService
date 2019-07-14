package com.fundation.webservice.model;

import java.io.*;
import java.security.MessageDigest;

public class Checksum {
    final static private int ARRAY_SIZE = 1024;
    final static private int BASE = 16;

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

    public static String getChecksum(String filename, String algorithm) throws Exception {
        byte[] buffer = createChecksum(filename,algorithm);
        String result = "";
        for (int arrayIndex=0; arrayIndex < buffer.length; arrayIndex++) {
            result += Integer.toString( ( buffer[arrayIndex] & 0xff ) + 0x100, BASE).substring( 1 );
        }
        return result;
    }

//    public static void main(String args[]) {
//        try {
//            System.out.println(getChecksum("C:\\Users\\LimbertVargas\\Desktop\\Limbert\\limbert\\limbert.mp3"));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
