package com.fundation.webservice.model;

public class Main {
    public static void main(String[] args) {
        CriteriaMetadata criteriaMetadata2 = new CriteriaMetadata();
        criteriaMetadata2.setSrcPath("\\Users\\AlejandroSanchez\\Desktop\\alszla\\_i\\exiftool\\exiftool.exe");
        Metadata metadata2 = new Metadata(criteriaMetadata2);
        metadata2.showInfo();
        Metadata metadata3 = new Metadata(criteriaMetadata2);
        metadata3.showInfo();
    }
}
