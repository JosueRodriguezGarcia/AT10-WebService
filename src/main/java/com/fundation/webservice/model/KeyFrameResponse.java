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

    public KeyFrameResponse(String fileName, String fileDownloadUri, String fileType, long size,
                         String newFormat, String frame, String nameOutputFile, String extOutputFile) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.newFormat = newFormat;

        this.frame = frame;
        this.nameOutputFile = nameOutputFile;
        this.extOutputFile = extOutputFile;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public long getSize() {
        return size;
    }

    public String getNewFormat() {
        return newFormat;
    }

    public String getFrame() {
        return frame;
    }

    public String getNameOutputFile() {
        return nameOutputFile;
    }

    public String getExtOutputFile() {
        return extOutputFile;
    }
}
