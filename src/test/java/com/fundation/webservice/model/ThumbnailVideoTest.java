package com.fundation.webservice.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ThumbnailVideoTest {

    @Test
    public void convert() {
        CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
        criteriaThumbnailVideo.setSrcPath(Directories.RSRC_DIR.getDir() + "videoTest.ogv");
        criteriaThumbnailVideo.setDestPath(Directories.RSRC_DIR.getDir());
        criteriaThumbnailVideo.setTime("30");
        criteriaThumbnailVideo.setName("thumbnail");
        criteriaThumbnailVideo.setExt("bmp");
        ThumbnailVideo thumbnailVideo = new ThumbnailVideo();
        thumbnailVideo.convert(criteriaThumbnailVideo);
        File outputFile = new File(criteriaThumbnailVideo.getDestPath() + criteriaThumbnailVideo.getName() + "."
                + criteriaThumbnailVideo.getExt());
        Boolean actual =  outputFile.exists();
        assertTrue(actual);
    }
}