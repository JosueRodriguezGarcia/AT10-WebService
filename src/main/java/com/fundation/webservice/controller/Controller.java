/*
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
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Implements the REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba
 * @version 1.0
 */
@RestController
public class Controller {
    // @Services injection through Spring @Autowired
    @Autowired
    private UploadService uploadService;
    @Autowired
    private DownloadService downloadService;

    Checksum checksum = new Checksum();

    // Default Request is a GET method
    @RequestMapping("/")
    public String home() {
        return "AT-10 File Conversion Service";
    }

    // POST asset to be converted along with the required conversion criteria.
    @PostMapping("/uploadPdf")
    public PdfResponse upload(@RequestParam("pdf") MultipartFile pdf,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "dpi", defaultValue = "") String dpi,
                              @RequestParam(value = "extension", defaultValue = "") String ext,
                              @RequestParam(value = "formatColor", defaultValue = "") String formatColor) {
        String pdfName = uploadService.storeFile(pdf);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name + ".zip")
                .toUriString();
        //creating a new folder for the converted images
        new File("C:/_pg/tmp/conversions/" + name + "/").mkdirs();
        //Converting pdf into images
        CriteriaPdfToImage criterion = new CriteriaPdfToImage();
        criterion.setSrcPath("C:\\_pg\\tmp\\uploads\\" + pdfName);
        criterion.setDestPath("C:\\_pg\\tmp\\conversions\\" + name + "\\");
        criterion.setName(name);
        criterion.setDpi(new Integer(dpi));
        criterion.setExt(ext);
        criterion.setFormatColor(formatColor);
        ConvertPdfToImage pdfDocument = new ConvertPdfToImage(criterion);
        pdfDocument.convert();
        //This line compresses the folder with images in a zip file
        FolderZipped.zipFolder(name);
        return new PdfResponse(pdfName, fileDownloadUri, pdf.getContentType(),
                pdf.getSize(), name, dpi, ext, formatColor);
    }

    // POST asset to be converted along with the required conversion criteria.
    @PostMapping("/uploadVideo")
    public VideoResponse upload(@RequestParam("video") MultipartFile file,
                                @RequestParam(value = "newFormat", defaultValue = "") String newFormat,
                                @RequestParam(value = "acodec", defaultValue = "") String acodec,
                                @RequestParam(value = "aBit", defaultValue = "") String aBit,
                                @RequestParam(value = "aChannel", defaultValue = "") String aChannel,
                                @RequestParam(value = "aRate", defaultValue = "") String aRate,
                                @RequestParam(value = "vcodec", defaultValue = "") String vcodec,
                                @RequestParam(value = "vTag", defaultValue = "") String vTag,
                                @RequestParam(value = "vBit", defaultValue = "") String vBit,
                                @RequestParam(value = "vRate", defaultValue = "") String vRate,
                                @RequestParam(value = "newName", defaultValue = "") String newName,
                                @RequestParam(value = "ext", defaultValue = "") String extension) {
        String fileName = uploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(newName + extension)
                .toUriString();
        //Converting Video
        CriteriaVideo criterion = new CriteriaVideo();
        criterion.setSrcPath("C:\\_pg\\tmp\\uploads\\" + fileName);
        criterion.setDestPath("C:\\_pg\\tmp\\conversions\\" + newName + extension);
        criterion.setNewFormat(newFormat);
        criterion.setaCodec(acodec);
        criterion.setaBit(new Integer(aBit));
        criterion.setaChannel(new Integer(aChannel));
        criterion.setaRate(new Integer(aRate));
        criterion.setvCodec(vcodec);
        criterion.setvTag(vTag);
        criterion.setvBit(new Integer(vBit));
        criterion.setvRate(new Integer(vRate));
        VideoConvert video = new VideoConvert(criterion);
        video.convert();
        return new VideoResponse(fileName, fileDownloadUri, file.getContentType(),
                file.getSize(), newFormat, acodec, aBit, aChannel, aRate, vcodec, vTag, vBit, vRate);
    }

    // POST asset to be converted along with the required conversion criteria.
    /*@PostMapping("/uploadAudio")
    public AudioResponse upload(@RequestParam("audio") MultipartFile file,
            @RequestParam(value = "newFormat", defaultValue = "") String newFormat,
            @RequestParam(value = "acodec", defaultValue = "") String acodec,
            @RequestParam(value = "aBit", defaultValue = "") String aBit,
            @RequestParam(value = "aChannel", defaultValue = "") String aChannel,
            @RequestParam(value = "aRate", defaultValue = "") String aRate,
            @RequestParam(value = "newName", defaultValue = "") String newName,
            @RequestParam(value = "ext", defaultValue = "") String extension) {
        String fileName = uploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(newName + extension)
                .toUriString();
        //Converting Audio
        CriteriaAudio criterion = new CriteriaAudio();
        criterion.setSrcPath("C:\\_pg\\tmp\\uploads\\" + fileName);
        criterion.setDestPath("C:\\_pg\\tmp\\conversions\\" + newName + extension);
        criterion.setNewFormat(newFormat);
        criterion.setAudioCodec(acodec);
        criterion.setAudioBit(new Integer(aBit));
        criterion.setAudioChannel(new Integer(aChannel));
        criterion.setAudioRate(new Integer(aRate));
        AudioConvert audio = new AudioConvert(criterion);
        audio.convert();
        return new AudioResponse(fileName, fileDownloadUri, file.getContentType(),
                file.getSize(), newFormat, acodec, aBit, aChannel, aRate);
    }*/

    // prueba input, output y conf con audio
    @PostMapping("/convert")
    public AudioResponse convertInput(@RequestParam("asset") MultipartFile asset,
            @RequestParam("input") String[] input,
            @RequestParam("config") String[] config,
            @RequestParam("output") String[] output) {
        String fileName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(output[0] + ".zip").toUriString();

        String inputChecksumString = "";
        try {
            inputChecksumString = checksum.getChecksum("C:\\_pg\\tmp\\uploads\\pruebawav.wav" ,"MD5");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(input[0].equals(inputChecksumString)){
            new File("C:/_pg/tmp/conversions/" + output[0] + "/").mkdirs();
            CriteriaAudio criteria = new CriteriaAudio();
            criteria.setSrcPath("C:\\_pg\\tmp\\uploads\\" + fileName);
            criteria.setDestPath("C:\\_pg\\tmp\\conversions\\" + output[0] + "\\" + output[0] + output[1]);
            criteria.setNewFormat(config[0]);
            criteria.setAudioCodec(config[1]);
            criteria.setAudioBit(new Integer(config[2]));
            criteria.setAudioChannel(new Integer(config[3]));
            criteria.setAudioRate(new Integer(config[4]));
            AudioConvert audio = new AudioConvert(criteria);
            audio.convert();

            String outputChecksumString = "";

            try {
                outputChecksumString = checksum.getChecksum("C:\\_pg\\tmp\\conversions\\limbert\\limbert.mp3","MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }

            File convertedFile = new File("C:\\_pg\\tmp\\conversions\\" + output[0] + "\\" + output[0] + output[1]);

            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
            FolderZipped.zipFolder(output[0]);
            return new AudioResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(),
                    config[0], config[1], config[2], config[3], config[4], outputChecksumString);
        } else {
            System.out.print("error");
            return null;
        }
    }

    // Endpoint for downloading converted assets
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        resource.getFilename() + "\"").body(resource);
    }
}
