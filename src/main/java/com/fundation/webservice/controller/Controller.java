/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.controller;

import com.fundation.webservice.model.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.json.JSONObject;

/**
 * Implements the REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Josue Rodriguez
 * @version 1.0
 */
@RestController
public class Controller {
    /**
     * @Services injection through Spring @Autowired
     */
    @Autowired
    private UploadService uploadService;
    @Autowired
    private DownloadService downloadService;

    /**
     * Default Request is a GET method
     *
     * @return a welcoming message
     */
    @RequestMapping("/")
    public String home() {
        return "AT-10 File Conversion Service";
    }

    Checksum checksum = new Checksum();
    Properties properties = new Properties();

    @PostMapping("/convert")
    public Response convert(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                            @RequestParam("config") String config, @RequestParam("output") String output) {

        JSONObject inputJson = new JSONObject(input);
        String convertType = inputJson.getString("typeConversion");
        if (convertType.equals("video")) {
            return this.video(asset, input, config, output);
        }
        return null;
    }

    /**
     * Video conversoin along with the required conversion criteria input, output and conf with video.
     *
     * @return all array string parameters.
     */
    public VideoResponse video(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                      @RequestParam("config") String config, @RequestParam("output") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        // Config values validation and default values assignment
        if (!configJson.has("audioCodec")) {
            configJson.put("audioCodec",VideoConfig.audioCodec.getValue());
        }
        if (!configJson.has("audioBitRate")) {
            configJson.put("audioBitRate",VideoConfig.audioBitRate.getValue());
        }
        if (!configJson.has("audioChannel")) {
            configJson.put("audioChannel",VideoConfig.audioChannel.getValue());
        }
        if (!configJson.has("videoCodec")) {
            configJson.put("videoCodec",VideoConfig.videoCodec.getValue());
        }
        if (!configJson.has("videoBitRate")) {
            configJson.put("videoBitRate",VideoConfig.videoBitRate.getValue());
        }
        if (!configJson.has("fps")) {
            configJson.put("fps",VideoConfig.fps.getValue());
        }
        if (!configJson.has("metadata")) {
            configJson.put("metadata",VideoConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail",VideoConfig.thumbnail.getValue());
        }
        if (!configJson.has("keyframes")) {
            configJson.put("keyframes",VideoConfig.keyframes.getValue());
        }

        String fileName = uploadService.storeFile(asset);

        String fileDownloadUri = "";
            if (!outputJson.has("destPath")) {
                fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
                        + ".zip").toUriString();
            }

        String inputChecksumString = "";
        InputStream inputProperties;

        try {
            inputProperties = new FileInputStream("application.properties");
            properties.load(inputProperties);
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            inputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir") + asset.getOriginalFilename(),
                    "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputJson.getString("checksum").equals(inputChecksumString)) {
            CriteriaVideo criteria = new CriteriaVideo();
            criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
            criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/" +
                    outputJson.getString("name") + outputJson.getString("ext"));
            criteria.setNewFormat(configJson.getString("newFormat"));
            criteria.setAudioCodec(configJson.getString("audioCodec"));
            criteria.setAudioBitRate(configJson.getInt("audioBitRate"));
            criteria.setAudioChannel(configJson.getInt("audioChannel"));
            criteria.setVideoCodec(configJson.getString("videoCodec"));
            criteria.setVideoBitRate(configJson.getInt("videoBitRate"));
            criteria.setFps(configJson.getInt("fps"));
            ConvertVideo video = new ConvertVideo();
            video.convert(criteria);
            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"), "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (configJson.getString("metadata").equals("json")) {
                //Create JSON
                File convertedFile = new File(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeJsonFile(convertedFile);
            }
            else if (configJson.getString("metadata").equals("xmp")) {
                //Create XMP
                File convertedFile = new File(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeXmpFile(convertedFile);
            }
            if (configJson.getBoolean("thumbnail")) {
                //Create thumbnail
                CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
                criteriaThumbnailVideo.setSrcPath(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                criteriaThumbnailVideo.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
                criteriaThumbnailVideo.setTime(configJson.getString("thumbnailTime"));
                criteriaThumbnailVideo.setName(outputJson.getString("name"));
                criteriaThumbnailVideo.setExt("bmp");
                ThumbnailVideo thumbnailVideo = new ThumbnailVideo();
                thumbnailVideo.convert(criteriaThumbnailVideo);
            }
            if (configJson.getBoolean("keyframes")) {
                //Create keyframes
                CriteriaKeyFrameVideo criteriaKeyFrameVideo = new CriteriaKeyFrameVideo();
                criteriaKeyFrameVideo.setSrcPath(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                criteriaKeyFrameVideo.setDestPath(properties.getProperty("file.conversionDir") +
                        outputJson.getString("name") + "/");
                criteriaKeyFrameVideo.setTime(configJson.getString("keyframeTime"));
                criteriaKeyFrameVideo.setName(outputJson.getString("name"));
                criteriaKeyFrameVideo.setExt("png");
                KeyFrameOfVideo keyFrameOfVideo = new KeyFrameOfVideo();
                keyFrameOfVideo.convert(criteriaKeyFrameVideo);
            }

            FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

            if (fileDownloadUri.isEmpty()) {
                File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
                File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
                try {
                    copyFile(resultZip, targetZip);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return new VideoResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(),
                    configJson.getString("newFormat"), configJson.getString("audioCodec"),
                    configJson.getString("audioBitRate"), configJson.getString("audioChannel"),
                    configJson.getString("videoCodec"), configJson.getString("videoBitRate"),
                    configJson.getString("fps"), configJson.getString("metadata"),
                    configJson.getBoolean("thumbnail"), configJson.getBoolean("keyframes"),
                    outputChecksumString);
        } else {
            System.out.print("Error");
            return null;
        }
    }

    /**
     * Endpoint for downloading converted assets
     *
     * @param fileName donload the convert file with added file name
     * @param request
     * @return
     */
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = downloadService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename()
                        + "\"").body(resource);
    }

    /**
     * This method copies a file to a target destination.
     *
     * @param sourceFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size - count)) < size) ;
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    /**
     * This method copies the contents of a directory to another.
     *
     * @param sourceLocation
     * @param targetLocation
     * @throws IOException
     */
    public static void copyFolder(File sourceLocation, File targetLocation) throws IOException {
        final int WRITE_BUFFER = 1024;
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            File[] files = sourceLocation.listFiles();
            for (File file : files) {
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(targetLocation + "/" + file.getName());

                // Copy the bits from input stream to output stream
                byte[] buffer = new byte[WRITE_BUFFER];
                int line;
                while ((line = in.read(buffer)) > 0) {
                    out.write(buffer, 0, line);
                }
                in.close();
                out.close();
            }
        }
    }

    /**
     * This method returns the name of a file without its extension.
     *
     * @param file File object as handler that points to a file based on a String file path.
     * @return the filename without extension
     */
    public String filenameWithoutExtension(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }
}
