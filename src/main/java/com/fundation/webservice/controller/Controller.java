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

import com.fundation.webservice.model.ConvertPdfToImage;
import com.fundation.webservice.model.CriteriaPdfToImage;
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
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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

    // Default Request is a GET method
    @RequestMapping("/")
    public String home() {
        return "AT-10 File Conversion Service";
    }

    // POST asset to be converted along with the required conversion criteria.
    @PostMapping("/uploadPdf")
    public pdfResponse upload(@RequestParam("pdf") MultipartFile pdf,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "dpi", defaultValue = "") String dpi,
                              @RequestParam(value = "extension", defaultValue = "") String ext,
                              @RequestParam(value = "formatColor", defaultValue = "") String formatColor){
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
        criterion.setDestPath("C:\\_pg\\tmp\\conversions\\"+name + "\\");
        criterion.setName(name);
        criterion.setDpi(new Integer(dpi));
        criterion.setExt(ext);
        criterion.setFormatColor(formatColor);
        ConvertPdfToImage cuento = new ConvertPdfToImage(criterion);
        cuento.convert();

        //This line compresses the folder with images in a zip file
        FolderZipped.zipFolder(name);

        return new pdfResponse(pdfName, fileDownloadUri, pdf.getContentType(),
                pdf.getSize(), name, dpi, ext, formatColor);
    }


    // Endpoint for downloading converted assets
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = downloadService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } 
        catch (Exception ex) {
            
        }

        // Default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
            resource.getFilename() + "\"").body(resource);
    }
}
