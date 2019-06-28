package com.fundation.webservice.controller;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String vcodec;
    private String acodec;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String vcoded, String acodec) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.vcodec = vcodec;
        this.acodec = acodec;
    }

	// Getters and Setters 
    public String getfileName() {
        return this.fileName;
    }

    public String getFileDownloadUri() {
        return this.fileDownloadUri;
    }

    public String getFileType() {
        return this.fileType;
    }

    public long getSize() {
        return this.size;
    }
}
