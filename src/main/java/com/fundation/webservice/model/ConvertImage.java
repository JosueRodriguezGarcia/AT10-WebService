package com.fundation.webservice.model;

public class ConvertImage {
    CriteriaImage criteria;

    public ConvertImage(CriteriaImage criteria) {
        this.criteria = criteria;
    }

    public void convert() {
        String magick = "3rdparty/ImageMagic/magick ";
        try {
            String cmd = magick
                    + criteria.getSrcPath()
                    + " -resize " + criteria.getResolution()
                    + " -quality " + criteria.getQuality()+ " "
                    + criteria.getDestPath()
                    + criteria.getName() + "."
                    + criteria.getExt();
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
