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

import java.util.Properties;
import java.util.logging.Logger;

import org.json.JSONObject;

/**
 * Implements a REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Josue Rodriguez, Jesus Menacho.
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

    Checksum checksum = new Checksum();
    Properties properties = new Properties();

    String fileName = "";

    /**
     * Default Request is a GET method
     *
     * @return a welcoming message
     */
    @RequestMapping("/")
    public String home() {
        return "Welcome to the AT-10 File Conversion Service!";
    }

    /**
     * This is the central and ony endpoint. It redirects to other individual conversion processes according to what
     * the client asks for.
     *
     * @param asset The input file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input asset
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output product
     * @return a Response message depending on the type of conversion processed
     */
    @PostMapping("/convert")
    public Response convert(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        String convertType = inputJson.getString("typeConversion");
        InputStream inputProperties;
        try {
            inputProperties = new FileInputStream("application.properties");
            properties.load(inputProperties);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (inputJson.has("checksum")) {
            QueryDriver queryDriver = new QueryDriver();
            Logger logger = Logger.getLogger("Controller.class");

            if (queryDriver.verifyExist(inputJson.getString("checksum"))) {
                logger.info("This file already exists!");
            }
            else {
                logger.info("FILE SUCCESSFULLY UPLOADED.");
                fileName = uploadService.storeFile(asset);
                queryDriver.saveInfo(inputJson.getString("checksum"), properties.getProperty("file.uploadDir") +
                        asset.getOriginalFilename());
            }

            String inputChecksumString = "";
            try {
                inputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir") +
                    asset.getOriginalFilename(),"MD5");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            if (inputJson.getString("checksum").equals(inputChecksumString)) {
                if (convertType.equals("video")) {
                    return this.video(asset, input, config, output);
                }
                if ((convertType.equals("audio")) || (convertType.equals("videoToAudio"))) {
                    return this.audio(asset, input, config, output);
                }
                if (convertType.equals("pdfToImage")) {
                    return this.pdfToImage(asset, input, config, output);
                }
                if (convertType.equals("wordToPdf")) {
                    return this.wordToPdf(asset, input, config, output);
                }
                if (convertType.equals("pptToPdf")) {
                    return this.pptToPdf(asset, input, config, output);
                }
                if ((convertType.equals("wordToImage")) || (convertType.equals("pptToImage"))) {
                    return this.officeToImage(asset, input, config, output);
                }
                if (convertType.equals("imageToImage")) {
                    return this.imageToImage(asset, input, config, output);
                }
                if (convertType.equals("pdfToHtml")) {
                    return this.pdfToHtml(asset, input, config, output);
                }
                if (convertType.equals("pdfToDocx")) {
                    return this.pdfToDocx(asset, input, config, output);
                }
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Video transcoding process based on conversion criteria: input, config and output.
     *
     * @param asset The input video file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input video
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of transcoding.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output video
     * @return a Response specifying details on the output video product.
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

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") +
                outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

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
            criteriaThumbnailVideo.setDestPath(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/");
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

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
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
            configJson.getBoolean("thumbnail"), configJson.getBoolean("keyframes"), outputChecksumString);
    }

    /**
     * Audio/Video to audio transcoding process based on conversion criteria: input, config and output.
     *
     * @param asset The input audio/video file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input file
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of transcoding.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output audio
     * @return a Response specifying details on the output audio product.
     */
    public AudioResponse audio(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
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
        if (!configJson.has("metadata")) {
            configJson.put("metadata",VideoConfig.metadata.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        CriteriaAudio criteria = new CriteriaAudio();
        criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/" +
            outputJson.getString("name") + outputJson.getString("ext"));
        criteria.setNewFormat(configJson.getString("newFormat"));
        criteria.setAudioCodec(configJson.getString("audioCodec"));
        criteria.setAudioBitRate(new Integer(configJson.getString("audioBitRate")));
        criteria.setAudioChannel(new Integer(configJson.getString("audioChannel")));
        ConvertAudio audio = new ConvertAudio();
        audio.convert(criteria);

        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"), "MD5");
        }
        catch (Exception e) {
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
        else if (configJson.getString("metadata").equals("xmp")){
            //Create XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new AudioResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(),
            configJson.getString("newFormat"), configJson.getString("audioCodec"),
            configJson.getString("audioBitRate"), configJson.getString("audioChannel"),
            configJson.getString("metadata"),  outputChecksumString);
    }

    /**
     * PDF to image conversion based on criteria: input, config and output.
     *
     * @param asset The input pdf file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input file
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output images
     * @return a Response specifying details on the output images.
     */
    public PdfResponse pdfToImage(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        // Config values validation and default values assignment
        if (!configJson.has("dpi")) {
            configJson.put("dpi",PdfToImageConfig.dpi.getValue());
        }
        if (!configJson.has("formatColor")) {
            configJson.put("formatColor",PdfToImageConfig.formatColor.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail",PdfToImageConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        // Contrary to video and audio methods, pdfToImage needs the explicit creation (if not present) of the directory
        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaPdfToImage criteria = new CriteriaPdfToImage();
        criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setDpi(configJson.getInt("dpi"));
        criteria.setExt(outputJson.getString("ext"));
        criteria.setFormatColor(configJson.getString("formatColor"));
        ConvertPdfToImage pdfToImage = new ConvertPdfToImage();
        pdfToImage.convert(criteria);

        if (configJson.getBoolean("thumbnail")) {
            //Create thumbnail
            PdfThumbnail pdfToThumbnail = new PdfThumbnail();
            pdfToThumbnail.convert(criteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PdfResponse(fileName, fileDownloadUri, asset.getContentType(),
            asset.getSize(), outputJson.getString("name"), configJson.getString("dpi"),
            outputJson.getString("ext"), configJson.getString("formatColor"));
    }

    /**
     * MS Word to PDF document conversion based on criteria: input, config and output.
     *
     * @param asset The input pdf file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input file
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output file
     * @return a Response specifying details on the output pdf file.
     */
    public WordToPdfResponse wordToPdf(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        // Config values validation and default values assignment
        if (!configJson.has("metadata")) {
            configJson.put("metadata", OfficeToPdfConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail", OfficeToPdfConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        // Contrary to video and audio methods, pdfToImage needs the explicit creation (if not present) of the directory
        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaThumbnailImage criteria = new CriteriaThumbnailImage();
        criteria.setSrcPath(System.getenv("SystemDrive") + properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(System.getenv("SystemDrive") + properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") + outputJson.getString("ext"));
        ConvertPPTtoPdf converter = new ConvertPPTtoPdf();
        converter.convert(criteria);
        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configJson.getString("metadata").equals("json")) {
            //Creation JSON
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeJsonFile(convertedFile);
        }
        else if (configJson.getString("metadata").equals("xmp")) {
            //Creation XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }
        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            CriteriaPdfToImage pdfToImgCriteria = new CriteriaPdfToImage();
            pdfToImgCriteria.setSrcPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                    "/" + outputJson.getString("name") + outputJson.getString("ext"));
            pdfToImgCriteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                    "/");
            pdfToImgCriteria.setName(outputJson.getString("name"));
            pdfToImgCriteria.setExt(".jpg");
            pdfToImgCriteria.setDpi(150);
            pdfToImgCriteria.setFormatColor("RGB");
            PdfThumbnail pdfToThumb = new PdfThumbnail();
            pdfToThumb.convert(pdfToImgCriteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new WordToPdfResponse(fileName, fileDownloadUri, outputChecksumString);
    }

    /**
     * MS PowerPoint to PDF document conversion based on criteria: input, config and output.
     *
     * @param asset The input pdf file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input file
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output file
     * @return a Response specifying details on the output pdf file.
     */
    public PPTtoPdfResponse pptToPdf(@RequestParam("asset") MultipartFile asset,
                                     @RequestParam(value = "input", defaultValue = "{}") String input,
                                     @RequestParam(value = "config", defaultValue = "{}") String config,
                                     @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        // Config values validation and default values assignment
        if (!configJson.has("metadata")) {
            configJson.put("metadata", OfficeToPdfConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail", OfficeToPdfConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        // Contrary to video and audio methods, pdfToImage needs the explicit creation (if not present) of the directory
        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaThumbnailImage criteria = new CriteriaThumbnailImage();
        criteria.setSrcPath(System.getenv("SystemDrive") + properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(System.getenv("SystemDrive") + properties.getProperty("file.conversionDir") +
            outputJson.getString("name") + "/" + outputJson.getString("name") + outputJson.getString("ext"));
        ConvertPPTtoPdf converter = new ConvertPPTtoPdf();
        converter.convert(criteria);
        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configJson.getString("metadata").equals("json")) {
            //Creation JSON
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeJsonFile(convertedFile);
        }
        else if (configJson.getString("metadata").equals("xmp")) {
            //Creation XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }
        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            CriteriaPdfToImage pdfToImgCriteria = new CriteriaPdfToImage();
            pdfToImgCriteria.setSrcPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                "/" + outputJson.getString("name") + outputJson.getString("ext"));
            pdfToImgCriteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                "/");
            pdfToImgCriteria.setName(outputJson.getString("name"));
            pdfToImgCriteria.setExt(".jpg");
            pdfToImgCriteria.setDpi(150);
            pdfToImgCriteria.setFormatColor("RGB");
            PdfThumbnail pdfToThumb = new PdfThumbnail();
            pdfToThumb.convert(pdfToImgCriteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PPTtoPdfResponse(fileName, fileDownloadUri, outputChecksumString);
    }

    /**
     * MS documents to images conversion based on criteria: input, config and output.
     *
     * @param asset The input pdf file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input document
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output files
     * @return a Response specifying details on the output images.
     */
    public PdfResponse officeToImage(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        // Config values validation and default values assignment
        if (!configJson.has("dpi")) {
            configJson.put("dpi",PdfToImageConfig.dpi.getValue());
        }
        if (!configJson.has("formatColor")) {
            configJson.put("formatColor",PdfToImageConfig.formatColor.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail", PdfToImageConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        // Contrary to video and audio methods, pdfToImage needs the explicit creation (if not present) of the directory
        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaPdfToImage criteria = new CriteriaPdfToImage();
        criteria.setSrcPath(System.getenv("SystemDrive") + properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(System.getenv("SystemDrive") + properties.getProperty("file.conversionDir") +
            outputJson.getString("name") + "/" + outputJson.getString("name") + ".pdf");
        ConvertPPTtoPdf converter = new ConvertPPTtoPdf();
        converter.convert(criteria);

        criteria.setSrcPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
            "/" + outputJson.getString("name") + ".pdf");
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setExt(outputJson.getString("ext"));
        criteria.setDpi(configJson.getInt("dpi"));
        criteria.setFormatColor(configJson.getString("formatColor"));
        ConvertPdfToImage pdfToImage = new ConvertPdfToImage();
        pdfToImage.convert(criteria);

        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            PdfThumbnail pdfToThumb = new PdfThumbnail();
            pdfToThumb.convert(criteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PdfResponse(fileName, fileDownloadUri, asset.getContentType(),
                asset.getSize(), outputJson.getString("name"), configJson.getString("dpi"),
                outputJson.getString("ext"), configJson.getString("formatColor"));
    }

    /**
     * Converts from an image format to antoher image format based on a user-provided criteria: input, config and output.
     *
     * @param asset The input pdf file itself
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input image
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output image
     * @return a Response specifying details on the output image(s).
     */
    public PPTtoPdfResponse imageToImage(@RequestParam("asset") MultipartFile asset,
                                  @RequestParam(value = "input", defaultValue = "{}") String input,
                                  @RequestParam(value = "config", defaultValue = "{}") String config,
                                  @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        if (!configJson.has("resolution")) {
            configJson.put("resolution",ImageConfig.resolution.getValue());
        }
        if (!configJson.has("rotation")) {
            configJson.put("rotation",ImageConfig.rotation.getValue());
        }
        if (!configJson.has("quality")) {
            configJson.put("quality",ImageConfig.quality.getValue());
        }
        if (!configJson.has("metadata")) {
            configJson.put("metadata",ImageConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail",ImageConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaImage criteria = new CriteriaImage();
        criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setExt(outputJson.getString("ext"));
        criteria.setResolution(configJson.getString("resolution"));
        criteria.setRotation(configJson.getInt("rotation"));
        criteria.setQuality(configJson.getInt("quality"));
        ConvertImage converter = new ConvertImage();
        converter.convert(criteria);

        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configJson.getString("metadata").equals("json")) {
            //Creation JSON
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeJsonFile(convertedFile);
        }
        else if (configJson.getString("metadata").equals("xmp")) {
            //Creation XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }
        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            CriteriaThumbnailImage criteriaThumb = new CriteriaThumbnailImage();
            criteriaThumb.setSrcPath(criteria.getSrcPath());
            criteriaThumb.setDestPath(criteria.getDestPath());
            criteriaThumb.setName(criteria.getName());
            criteriaThumb.setExt(".jpg");
            ThumbnailImage thumb = new ThumbnailImage();
            thumb.convert(criteriaThumb);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PPTtoPdfResponse(fileName, fileDownloadUri, outputChecksumString);
    }

    /**
     * Converts from a pdf document to an html page based on a user-provided criteria: input, config and output.
     *
     * @param asset The input pdf file itself.
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input image.
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output page.
     * @return a Response specifying details on the output page(s).
     */
    public PPTtoPdfResponse pdfToHtml(@RequestParam("asset") MultipartFile asset,
            @RequestParam(value = "input", defaultValue = "{}") String input,
            @RequestParam(value = "config", defaultValue = "{}") String config,
            @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        if (!configJson.has("metadata")) {
            configJson.put("metadata",ImageConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail",ImageConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        CriteriaThumbnailImage criteria = new CriteriaThumbnailImage();
        criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setExt(outputJson.getString("ext"));
        ConvertPdfToHtml converter = new ConvertPdfToHtml();
        converter.convert(criteria);

        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configJson.getString("metadata").equals("json")) {
            //Creation JSON
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeJsonFile(convertedFile);
        }
        else if (configJson.getString("metadata").equals("xmp")) {
            //Creation XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                outputJson.getString("name") + "/" + outputJson.getString("name") +
                outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }
        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            CriteriaPdfToImage pdfToImgCriteria = new CriteriaPdfToImage();
            pdfToImgCriteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
            pdfToImgCriteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                "/");
            pdfToImgCriteria.setName(outputJson.getString("name"));
            pdfToImgCriteria.setExt(".jpg");
            pdfToImgCriteria.setDpi(150);
            pdfToImgCriteria.setFormatColor("RGB");
            PdfThumbnail pdfToThumb = new PdfThumbnail();
            pdfToThumb.convert(pdfToImgCriteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PPTtoPdfResponse(fileName, fileDownloadUri, outputChecksumString);
    }

    /**
     * Converts from a PDF document to to a MS Word document based on a user-provided criteria: input, config and output.
     *
     * @param asset The input pdf document itself.
     * @param input A JSON formatted string (wo \n characters) that holds all the parameters tied to the input document.
     * @param config A JSON formatted string (wo \n characters) that holds all the parameters tied to the configuration
     *               of the particular process of conversion.
     * @param output A JSON formatted string (wo \n characters) that holds all the parameters tied to the output document.
     * @return a Response specifying details on the output file.
     */
    public PPTtoPdfResponse pdfToDocx(@RequestParam("asset") MultipartFile asset,
                                      @RequestParam(value = "input", defaultValue = "{}") String input,
                                      @RequestParam(value = "config", defaultValue = "{}") String config,
                                      @RequestParam(value = "output", defaultValue = "{}") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        if (!configJson.has("metadata")) {
            configJson.put("metadata",ImageConfig.metadata.getValue());
        }
        if (!configJson.has("thumbnail")) {
            configJson.put("thumbnail",ImageConfig.thumbnail.getValue());
        }

        String fileDownloadUri = "";
        if (!outputJson.has("destPath")) {
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(outputJson.getString("name") + ".zip").toUriString();
        }
        else {
            File destPathFile = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            fileDownloadUri = destPathFile.toURI().toString() ;
        }

        new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/").mkdirs();

        // Two-step process
        // 1st step: From PDF to HTML
        CriteriaThumbnailImage criteria = new CriteriaThumbnailImage();
        criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setExt(".html");
        ConvertPdfToHtml pdfToHtmlConverter = new ConvertPdfToHtml();
        pdfToHtmlConverter.convert(criteria);
        // 2nd step: From HTML to MS Word
        criteria.setSrcPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/" +
            outputJson.getString("name") + ".html");
        criteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") + "/");
        criteria.setName(outputJson.getString("name"));
        criteria.setExt(outputJson.getString("ext"));
        ConvertHtmlToDoc htmlToDocConverter = new ConvertHtmlToDoc();
        htmlToDocConverter.convert(criteria);

        String outputChecksumString = "";
        try {
            outputChecksumString = checksum.getChecksum(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configJson.getString("metadata").equals("json")) {
            //Creation JSON
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeJsonFile(convertedFile);
        }
        else if (configJson.getString("metadata").equals("xmp")) {
            //Creation XMP
            File convertedFile = new File(properties.getProperty("file.conversionDir") +
                    outputJson.getString("name") + "/" + outputJson.getString("name") +
                    outputJson.getString("ext"));
            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
        }
        if (configJson.getBoolean("thumbnail")) {
            //Creation thumbnail
            CriteriaPdfToImage pdfToImgCriteria = new CriteriaPdfToImage();
            pdfToImgCriteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
            pdfToImgCriteria.setDestPath(properties.getProperty("file.conversionDir") + outputJson.getString("name") +
                    "/");
            pdfToImgCriteria.setName(outputJson.getString("name"));
            pdfToImgCriteria.setExt(".jpg");
            pdfToImgCriteria.setDpi(150);
            pdfToImgCriteria.setFormatColor("RGB");
            PdfThumbnail pdfToThumb = new PdfThumbnail();
            pdfToThumb.convert(pdfToImgCriteria);
        }

        // Zip all the files in the conversion directory
        FolderZipped.zipFolder(properties.getProperty("file.conversionDir") + outputJson.getString("name"));

        // If a target location has been specified, copy the zip file there.
        if (outputJson.has("destPath")) {
            File resultZip = new File(properties.getProperty("file.conversionDir") + outputJson.getString("name") + ".zip");
            File targetZip = new File(outputJson.getString("destPath") + outputJson.getString("name") + ".zip");
            try {
                copyFile(resultZip, targetZip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PPTtoPdfResponse(fileName, fileDownloadUri, outputChecksumString);
    }

    /**
     * Endpoint for downloading zip file containing the products of a conversion process.
     *
     * @param fileName Name of the output zip file.
     * @param request HTTP GET verb.
     * @return a JSON formatted Response providing the URI of the resulting zip file.
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
     * @return the filename without extension.
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
