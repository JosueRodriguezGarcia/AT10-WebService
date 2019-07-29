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

/**
 * Implements a response to a transcoding request.
 *
 * @author Maday Alcala Cuba, Josue Rodriguez Garcia.
 * @version 1.0
 */
public class AudioResponse extends Response{
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;
    private String aCodec;
    private String aBitRate;
    private String aChannel;
    private String checksum;

    /**
     * Constructor of the audio response class
     * Initializes its attributes.
     *
     * @param fileName        new name for ouptup file.
     * @param fileDownloadUri url for dwonloader the output file .zip.
     * @param fileType        infoamtion the file type of output file.
     * @param size            show the size of output file.
     * @param newFormat       new format of output file convertetd.
     * @param aCodec          Codec use for convertion.
     * @param aBitRate        Bit rate of new output file
     * @param aChannel        number of channel for the new output file.
     * @param checksum        information of the checksum for output file.
     */
    public AudioResponse(String fileName, String fileDownloadUri, String fileType, long size,
            String newFormat, String aCodec, String aBitRate, String aChannel, String checksum) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;
        this.aCodec = aCodec;
        this.aBitRate = aBitRate;
        this.aChannel = aChannel;
        this.checksum = checksum;
    }

    /**
     * Method that return the name of outputFile
     *
     * @return name the output File.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Method that return URL of output file for Download.
     *
     * @return URI of the output File.
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     * Method that return the Type of data of the output file.
     *
     * @return Type the output File.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Method that return the size of output File.
     *
     * @return Size the output File.
     */
    public long getSize() {
        return size;
    }

    /**
     * Method that return the new format of the output File.
     *
     * @return new format of File
     */
    public String getNewFormat() {
        return newFormat;
    }

    /**
     * Method that return the type the codec for convert the input File.
     *
     * @return codec for convert.
     */
    public String getaCodec() {
        return aCodec;
    }

    /**
     * Method that return bitrate for convert the input File
     *
     * @return bitrate the bitrate output file.
     */
    public String getaBitRate() {
        return aBitRate;
    }

    /**
     * Method that return the number de channel for convert the input file.
     *
     * @return number the channel for output file.
     */
    public String getaChannel() {
        return aChannel;
    }

    /**
     * Method that return the checksum in format MD5 or SHA-1.
     *
     * @return checksum of the file.
     */
    public String getChecksum() {
        return checksum;
    }
}
