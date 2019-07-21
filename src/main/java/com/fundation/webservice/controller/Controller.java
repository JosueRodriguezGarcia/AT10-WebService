/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    Checksum checksum = new Checksum();

    Properties properties = new Properties();


    /**
     * Default Request is a GET method
     *
     * @return name of output message in postman
     */
    @RequestMapping("/")
    public String home() {
        return "AT-10 File Conversion Service";
    }

    /**
     * POST asset to be converted along with the required conversion criteria.
     *
     * @param pdf         defines upload file.
     * @param name        defines de name of the file output.
     * @param dpi         defines format of dot point for inch.
     * @param ext         define the extension of the output file.
     * @param formatColor defines the different format colors of the output file.
     * @return defines all parameters of the output file.
     */
    @PostMapping("/convertPdf")
    public PdfResponse upload(@RequestParam("pdf") MultipartFile pdf, @RequestParam(value = "name", defaultValue = "")
            String name, @RequestParam(value = "dpi", defaultValue = "") String dpi, @RequestParam(value = "extension",
            defaultValue = "") String ext, @RequestParam(value = "formatColor", defaultValue = "") String formatColor) {
        String pdfName = uploadService.storeFile(pdf);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(name + ".zip").toUriString();

        /**
         * creating a new folder for the converted images
         */
        new File("C:/_pg/tmp/conversions/" + name + "/").mkdirs();
        /**
         * Converting pdf into images
         */
        CriteriaPdfToImage criterion = new CriteriaPdfToImage();
        criterion.setSrcPath("C:\\_pg\\tmp\\uploads\\" + pdfName);
        criterion.setDestPath("C:\\_pg\\tmp\\conversions\\" + name + "\\");
        criterion.setName(name);
        criterion.setDpi(new Integer(dpi));
        criterion.setExt(ext);
        criterion.setFormatColor(formatColor);
        ConvertPdfToImage pdfDocument = new ConvertPdfToImage();
        pdfDocument.convert(criterion);
        /**
         * This line compresses the folder with images in a zip file
         */
        FolderZipped.zipFolder(name);
        return new PdfResponse(pdfName, fileDownloadUri, pdf.getContentType(),
                pdf.getSize(), name, dpi, ext, formatColor);
    }

    /**
     * POST asset to be converted along with the required conversion criteria input, output and conf with video.
     *
     * @param asset  defines upload file.
     * @param input  defines (at the moment) the checksum of the upload file.
     * @param config defines all the configurations for the output file.
     * @param output defines the name and the extension of the output result file.
     * @return all array string parameters.
     */
    @PostMapping("/convertVideo")
    public VideoResponse convertVideo(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String[] input,
                                      @RequestParam("config") String[] config, @RequestParam("output") String[] output) {
        String fileName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(output[0]
                + ".zip").toUriString();
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
        if (input[0].equals(inputChecksumString)) {
            new File(properties.getProperty("file.downloadDir") + output[0] + "/").mkdirs();
            CriteriaVideo criteria = new CriteriaVideo();
            criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
            criteria.setDestPath(properties.getProperty("file.downloadDir") + output[0] + "/" + output[0] + output[1]);
            criteria.setNewFormat(config[0]);
            criteria.setAudioCodec(config[1]);
            criteria.setAudioBitRate(new Integer(config[2]));
            criteria.setAudioChannel(new Integer(config[3]));
//            criteria.setAudioRate(new Integer(config[4]));
            criteria.setVideoCodec(config[4]);
//            criteria.setVideoTag(config[6]);
            criteria.setVideoBitRate(new Integer(config[5]));
            criteria.setFps(new Integer(config[6]));
            ConvertVideo video = new ConvertVideo();
            video.convert(criteria);
            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(properties.getProperty("file.downloadDir") + output[0] + "/"
                        + output[0] + output[1], "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (config[7].equals("json")) {
                //Creation JSON
                File convertedFile = new File(properties.getProperty("file.downloadDir") + output[0] + "/" + output[0] + output[1]);
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeJsonFile(convertedFile);
            } else {
                //Creation XMP
                File convertedFile = new File(properties.getProperty("file.downloadDir") + output[0] + "/" + output[0] + output[1]);
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeXmpFile(convertedFile);
            }
            if (config[8].equals("True")) {
                //Creation thumbnail
                CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
                criteriaThumbnailVideo.setSrcPath(properties.getProperty("file.downloadDir") + output[0] + "/" + output[0] + output[1]);
                criteriaThumbnailVideo.setDestPath(properties.getProperty("file.downloadDir") + output[0] + "/");
                criteriaThumbnailVideo.setTime(config[9]);
                criteriaThumbnailVideo.setName(output[0]);
                criteriaThumbnailVideo.setExt("bmp");
                ThumbnailVideo thumbnailVideo = new ThumbnailVideo(criteriaThumbnailVideo);
                thumbnailVideo.convert();
            }
            if(config[10].equals("True")){
                //Creation keyframes
                CriteriaKeyFrameVideo criteriaKeyFrameVideo = new CriteriaKeyFrameVideo();
                criteriaKeyFrameVideo.setSrcPath(properties.getProperty("file.downloadDir") + output[0] + "/" + output[0] + output[1]);
                criteriaKeyFrameVideo.setDestPath(properties.getProperty("file.downloadDir") + output[0] + "/");
                criteriaKeyFrameVideo.setTime(config[11]);
                criteriaKeyFrameVideo.setName(output[0]);
                criteriaKeyFrameVideo.setExt("png");
                KeyFrameOfVideo keyFrameOfVideo = new KeyFrameOfVideo(criteriaKeyFrameVideo);
                keyFrameOfVideo.convert();
            }

            FolderZipped.zipFolder(properties.getProperty("file.downloadDir")+output[0]);

            return new VideoResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(), config[0],
                    config[1], config[2], config[3], config[4], config[5], config[6], config[7], config[8],config[10],
                    outputChecksumString);
        } else {
            System.out.print("Error");
            return null;
        }
    }

    /**
     * POST asset to be converted along with the required conversion criteria input, output y conf with audio.
     *
     * @param asset  defines upload file.
     * @param input  defines (at the moment) the checksum of the upload file.
     * @param config defines all the configurations for the output file.
     * @param output defines the name and the extension of the output result file.
     * @return all array string parameters.
     */
    @PostMapping("/convertAudio")
    public AudioResponse convertAudio(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String[] input,
                                      @RequestParam("config") String[] config, @RequestParam("output") String[] output) {
        String fileName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(output[0]
                + ".zip").toUriString();
        String inputChecksumString = "";

        try {
            inputChecksumString = checksum.getChecksum("C:\\_pg\\tmp\\uploads\\pruebawav.wav", "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (input[0].equals(inputChecksumString)) {
            new File("C:/_pg/tmp/conversions/" + output[0] + "/").mkdirs();
            CriteriaAudio criteria = new CriteriaAudio();
            criteria.setSrcPath("C:\\_pg\\tmp\\uploads\\" + fileName);
            criteria.setDestPath("C:\\_pg\\tmp\\conversions\\" + output[0] + "\\" + output[0] + output[1]);
            criteria.setNewFormat(config[0]);
            criteria.setAudioCodec(config[1]);
            criteria.setAudioBit(new Integer(config[2]));
            criteria.setAudioChannel(new Integer(config[3]));
            criteria.setAudioRate(new Integer(config[4]));
            ConvertAudio audio = new ConvertAudio();
            audio.convert(criteria);

            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum("C:\\_pg\\tmp\\conversions\\limbert\\limbert.mp3",
                        "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }

            File convertedFile = new File("C:\\_pg\\tmp\\conversions\\" + output[0] + "\\" + output[0]
                    + output[1]);

            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
            FolderZipped.zipFolder(output[0]);
            return new AudioResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(), config[0],
                    config[1], config[2], config[3], config[4], outputChecksumString);
        } else {
            System.out.print("error");
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

    @PostMapping("/convertKeyframe")
    public KeyFrameResponse convertKeyFrame(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String[] input,
                                            @RequestParam("config") String[] config, @RequestParam("output") String[] output) {
        String fileName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(output[0]
                + ".zip").toUriString();
        String inputChecksumString = "";
        try {
            inputChecksumString = checksum.getChecksum("C:\\_pg\\tmp\\uploads\\" + asset.getOriginalFilename(),
                    "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (input[0].equals(inputChecksumString)) {
            new File("C:/_pg/tmp/conversions/" + output[0] + "/").mkdirs();
            CriteriaKeyFrameVideo criteria = new CriteriaKeyFrameVideo();
            criteria.setSrcPath("C:\\_pg\\tmp\\uploads\\" + fileName);
            criteria.setDestPath("C:\\_pg\\tmp\\conversions\\" + output[0] + "\\");
            criteria.setTime(config[0]);
            criteria.setName(output[0]);
            criteria.setExt(output[1]);
            KeyFrameOfVideo keyFrameOfVideo = new KeyFrameOfVideo(criteria);
            keyFrameOfVideo.convert();
            FolderZipped.zipFolder(output[0]);

            return new KeyFrameResponse(fileName, fileDownloadUri);
        } else {
            System.out.print("Error");
            return null;
        }
    }
}
