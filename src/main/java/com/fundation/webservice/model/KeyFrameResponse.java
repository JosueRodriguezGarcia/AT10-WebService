package com.fundation.webservice.model;

public class KeyFrameResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String newFormat;

    private String frame;
    private String nameOutputFile;
    private String extOutputFile;
    private String checksum;

    public KeyFrameResponse(String fileName, String fileDownloadUri) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

}
