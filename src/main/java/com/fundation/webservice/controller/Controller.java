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
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Properties;

import org.json.JSONObject;

/**
 * Implements the REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sanchez Luizaga, Maday Alcala Cuba, Limbert Vargas, Josue Rodriguez, Jesus Menacho
 * @version 1.0
 */
@RestController
public class Controller {

    /**
     * This method copy file in other destinations.
     *
     * @param sourceFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel origen = null;
        FileChannel destino = null;
        try {
            origen = new FileInputStream(sourceFile).getChannel();
            destino = new FileOutputStream(destFile).getChannel();

            long count = 0;
            long size = origen.size();
            while ((count += destino.transferFrom(origen, count, size - count)) < size) ;
        } finally {
            if (origen != null) {
                origen.close();
            }
            if (destino != null) {
                destino.close();
            }
        }
    }

    /**
     * This method let extrac the extension to the file.
     *
     * @param file
     * @return
     */
    public String filenameWithoutExtension(File file) {
        String filenameWithoutExtension = null;
        int dotPosition = file.getName().lastIndexOf(".");
        if (dotPosition != -1) {
            filenameWithoutExtension = file.getName().substring(0, dotPosition);
        }
        return filenameWithoutExtension;
    }

    /**
     * This method copy all contain in one carpet to other.
     *
     * @param sourceLocation
     * @param targetLocation
     * @throws IOException
     */
    public static void copyFiles(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            File[] files = sourceLocation.listFiles();
            for (File file : files) {
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(targetLocation + "/" + file.getName());

                // Copy the bits from input stream to output stream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
    }

    /**
     * @Services injection through Spring @Autowired
     */
    @Autowired
    private UploadService uploadService;
    @Autowired
    private DownloadService downloadService;

    Checksum checksum = new Checksum();
    Properties properties = new Properties();

    @PostMapping("/convert")
    public Response convert(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                            @RequestParam("config") String config, @RequestParam("output") String output) {

        JSONObject inputJson = new JSONObject(input);
        String convertType = inputJson.getString("typeConversion");
        if ("video".equals(convertType)) {
            return this.convertVideo(asset, input, config, output);
        }
        if ("audio".equals(convertType)) {
            return this.convertAudio(asset, input, config, output);
        }
        if ("pdfToImage".equals(convertType)) {
            return this.pdfToImage(asset, input, config, output);
        }
        if ("wordToImage".equals(convertType)) {
            return this.WordToImage(asset, input, config, output);
        }
        if ("wordToPdf".equals(convertType)) {
            return this.WordToPdf(asset, input, config, output);
        }
        if ("videoToAudio".equals(convertType)) {
            return this.VideoAudio(asset, input, config, output);
        }
        return null;
    }


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
     * This method convert to pdf file to image file.
     *
     * @param asset
     * @param input
     * @param config
     * @param output
     * @return
     */
    public PdfResponse pdfToImage(@RequestParam("asset") MultipartFile asset, @RequestParam(value = "input", defaultValue = "")
            String input, @RequestParam(value = "config", defaultValue = "") String config, @RequestParam(value = "output",
            defaultValue = "") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);
        String pdfName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(outputJson.getString("name") + ".zip").toUriString();

        /**
         * creating a new folder for the converted images
         */
        new File(inputJson.getString("destPath") + outputJson.getString("name") + "/").mkdirs();
        /**
         * Converting pdf into images
         */
        String inputChecksumString = "";
        try {
            inputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir") + asset.getOriginalFilename(),
                    "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputJson.getString("checksum").equals(inputChecksumString)) {
            CriteriaPdfToImage criterion = new CriteriaPdfToImage();
            criterion.setSrcPath(properties.getProperty("file.uploadDir") + pdfName);
            //criterion.setDestPath(properties.getProperty("file.downloadDir") + outputJson.getString("name") + "\\");
            criterion.setDestPath(inputJson.getString("destPath") + outputJson.getString("name") + "\\");
            criterion.setName(outputJson.getString("name"));
            criterion.setDpi(new Integer(configJson.getString("dpi")));
            criterion.setExt(outputJson.getString("ext"));
            criterion.setFormatColor(configJson.getString("formatColor"));
            ConvertPdfToImage pdfDocument = new ConvertPdfToImage();
            pdfDocument.convert(criterion);
            /**
             * This line compresses the folder with images in a zip file
             */
            FolderZipped.zipFolder(outputJson.getString("name"));
        }

        return new PdfResponse(pdfName, fileDownloadUri, asset.getContentType(),
                asset.getSize(), outputJson.getString("name"), configJson.getString("dpi"), outputJson.getString("ext"), configJson.getString("formatColor"));
    }

    public WordToPdfResponse WordToImage(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                         @RequestParam("config") String config, @RequestParam("output") String output) {

        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        String fileName = uploadService.storeFile(asset);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
                + ".zip").toUriString();
        System.out.println(fileDownloadUri);

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

            CriteriaPdfToImage pruebaImagen = new CriteriaPdfToImage();
            pruebaImagen.setSrcPath(Directories.RSRCT_DIR.getDir() + fileName);
            pruebaImagen.setDestPath(Directories.RSRCT_DIR.getDir());
            pruebaImagen.setName(outputJson.getString("name"));
            pruebaImagen.setExt(outputJson.getString("ext"));
            pruebaImagen.setDpi(new Integer(configJson.getString("dpi")));
            pruebaImagen.setFormatColor(configJson.getString("formatColor"));
            ConvertWordToImage convertWordToImage = new ConvertWordToImage();
            convertWordToImage.convert(pruebaImagen);

            File a = new File(Directories.RSRCT_DIR.getDir());
            File b = new File(properties.getProperty("file.uploadDir"));

            try {
                copyFiles(a, b);
            } catch (IOException e) {
                e.printStackTrace();
            }

            File a2 = new File(properties.getProperty("file.uploadDir"));
            File b2 = new File(inputJson.getString("destPath"));
            try {
                copyFiles(a2, b2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                copyFiles(a2, b2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir").replace("/", "\\\\") + fileName, "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            FolderZipped.zipFolder(properties.getProperty("file.uploadDir"));
            return new WordToPdfResponse(fileName, fileDownloadUri, outputChecksumString);
        } else {
            return null;
        }
    }

    public WordToPdfResponse WordToPdf(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                       @RequestParam("config") String config, @RequestParam("output") String output) {

        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        String fileName = uploadService.storeFile(asset);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
                + ".zip").toUriString();
        System.out.println(fileDownloadUri);
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
            CriteriaConvert criteria = new CriteriaConvert();
            criteria.setSrcPath(properties.getProperty("file.uploadDir").replace("/", "\\\\") + fileName);
            ConvertWordToPdf convertWordToPdf = new ConvertWordToPdf();
            convertWordToPdf.convert(criteria);
            File a = new File(properties.getProperty("file.uploadDir"));
            File b = new File(inputJson.getString("destPath"));
            try {
                copyFiles(a, b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir").replace("/", "\\\\") + fileName, "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            FolderZipped.zipFolder(properties.getProperty("file.uploadDir"));
            return new WordToPdfResponse(fileName, fileDownloadUri, outputChecksumString);
        } else {
            return null;
        }

    }

    /**
     * POST asset to be converted along with the required conversion criteria input, output and conf with video.
     *
     * @return all array string parameters.
     */
    public VideoResponse convertVideo(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                      @RequestParam("config") String config, @RequestParam("output") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);
        String fileName = uploadService.storeFile(asset);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
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
        if (inputJson.getString("checksum").equals(inputChecksumString)) {
            CriteriaVideo criteria = new CriteriaVideo();
            criteria.setSrcPath(properties.getProperty("file.uploadDir") + fileName);
            criteria.setDestPath(inputJson.getString("destPath") + outputJson.getString("name") + "/" +
                    outputJson.getString("name") + outputJson.getString("ext"));
            criteria.setNewFormat(configJson.getString("newFormat"));
            criteria.setAudioCodec(configJson.getString("audioCodec"));
            criteria.setAudioBitRate(new Integer(configJson.getString("audioBitRate")));
            criteria.setAudioChannel(new Integer(configJson.getString("audioChannel")));
            criteria.setVideoCodec(configJson.getString("videoCodec"));
            criteria.setVideoBitRate(new Integer(configJson.getString("videoBitRate")));
            criteria.setFps(new Integer(configJson.getString("fps")));
            ConvertVideo video = new ConvertVideo();
            video.convert(criteria);
            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"), "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (configJson.getString("metadata").equals("json")) {
                //Creation JSON
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeJsonFile(convertedFile);
            } else {
                //Creation XMP
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeXmpFile(convertedFile);
            }
            if (configJson.getString("thumbnail").equals("True")) {
                //Creation thumbnail
                CriteriaThumbnailVideo criteriaThumbnailVideo = new CriteriaThumbnailVideo();
                criteriaThumbnailVideo.setSrcPath(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                criteriaThumbnailVideo.setDestPath(inputJson.getString("destPath") + outputJson.getString("name") + "/");
                criteriaThumbnailVideo.setTime(configJson.getString("thumbnailTime"));
                criteriaThumbnailVideo.setName(outputJson.getString("name"));
                criteriaThumbnailVideo.setExt("bmp");
                ThumbnailVideo thumbnailVideo = new ThumbnailVideo(criteriaThumbnailVideo);
                thumbnailVideo.convert();
            }
            if (configJson.getString("keyframe").equals("True")) {
                //Creation keyframes
                CriteriaKeyFrameVideo criteriaKeyFrameVideo = new CriteriaKeyFrameVideo();
                criteriaKeyFrameVideo.setSrcPath(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                criteriaKeyFrameVideo.setDestPath(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/");
                criteriaKeyFrameVideo.setTime(configJson.getString("keyframeTime"));
                criteriaKeyFrameVideo.setName(outputJson.getString("name"));
                criteriaKeyFrameVideo.setExt("png");
                KeyFrameOfVideo keyFrameOfVideo = new KeyFrameOfVideo(criteriaKeyFrameVideo);
                keyFrameOfVideo.convert();
            }

            FolderZipped.zipFolder(inputJson.getString("destPath") + outputJson.getString("name"));

            return new VideoResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(),
                    configJson.getString("newFormat"), configJson.getString("audioCodec"),
                    configJson.getString("audioBitRate"), configJson.getString("audioChannel"),
                    configJson.getString("videoCodec"), configJson.getString("videoBitRate"),
                    configJson.getString("fps"), configJson.getString("metadata"),
                    configJson.getString("thumbnail"), configJson.getString("keyframe"),
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
    public AudioResponse convertAudio(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                      @RequestParam("config") String config, @RequestParam("output") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        String fileName = uploadService.storeFile(asset);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
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
            inputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir") + asset.getOriginalFilename(), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputJson.getString("checksum").equals(inputChecksumString)) {
            new File(inputJson.getString("destPath") + outputJson.getString("name") + "/").mkdirs();
            CriteriaAudio criteria = new CriteriaAudio();
            criteria.setSrcPath(inputJson.getString("destPath") + fileName);

            criteria.setDestPath(inputJson.getString("destPath") + outputJson.getString("name") + "/" +
                    outputJson.getString("name") + outputJson.getString("ext"));
            criteria.setNewFormat(configJson.getString("newFormat"));
            criteria.setAudioCodec(configJson.getString("audioCodec"));
            criteria.setAudioBit(new Integer(configJson.getString("audioBitRate")));
            criteria.setAudioChannel(new Integer(configJson.getString("audioChannel")));
            criteria.setAudioRate(new Integer(configJson.getString("audioSampleRate")));
            ConvertAudio audio = new ConvertAudio();
            audio.convert(criteria);

            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"), "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (configJson.getString("metadata").equals("json")) {
                //Creation JSON
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeJsonFile(convertedFile);
            } else {
                //Creation XMP
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeXmpFile(convertedFile);
            }

            File convertedFile = new File(inputJson.getString("destPath") + outputJson.getString("name") + "\\" + outputJson.getString("name")
                    + outputJson.getString("ext"));

            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
            FolderZipped.zipFolder(inputJson.getString("destPath") + outputJson.getString("name"));
            return new AudioResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(), configJson.getString("newFormat"),
                    configJson.getString("audioCodec"), configJson.getString("audioBitRate"), configJson.getString("audioChannel"), configJson.getString("audioSampleRate"), outputChecksumString);
        } else {
            System.out.print("error");
            return null;
        }
    }

    /**
     * This method convert the video to audio.
     *
     * @param asset
     * @param input
     * @param config
     * @param output
     * @return
     */
    public VideoToAudioResponse VideoAudio(@RequestParam("asset") MultipartFile asset, @RequestParam("input") String input,
                                           @RequestParam("config") String config, @RequestParam("output") String output) {
        JSONObject inputJson = new JSONObject(input);
        JSONObject configJson = new JSONObject(config);
        JSONObject outputJson = new JSONObject(output);

        String fileName = uploadService.storeFile(asset);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(outputJson.getString("name")
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
            inputChecksumString = checksum.getChecksum(properties.getProperty("file.uploadDir") + asset.getOriginalFilename(), "MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputJson.getString("checksum").equals(inputChecksumString)) {
            new File(inputJson.getString("destPath") + outputJson.getString("name") + "/").mkdirs();
            CriteriaAudio criteria = new CriteriaAudio();
            criteria.setSrcPath(inputJson.getString("destPath") + fileName);

            criteria.setDestPath(inputJson.getString("destPath") + outputJson.getString("name") + "/" +
                    outputJson.getString("name") + outputJson.getString("ext"));
            criteria.setNewFormat(configJson.getString("newFormat"));
            criteria.setAudioCodec(configJson.getString("audioCodec"));
            criteria.setAudioBit(new Integer(configJson.getString("audioBitRate")));
            criteria.setAudioChannel(new Integer(configJson.getString("audioChannel")));
            criteria.setAudioRate(new Integer(configJson.getString("audioSampleRate")));
            ConvertAudio audio = new ConvertAudio();
            audio.convert(criteria);

            String outputChecksumString = "";
            try {
                outputChecksumString = checksum.getChecksum(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"), "MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (configJson.getString("metadata").equals("json")) {
                //Creation JSON
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeJsonFile(convertedFile);
            } else {
                //Creation XMP
                File convertedFile = new File(inputJson.getString("destPath") +
                        outputJson.getString("name") + "/" + outputJson.getString("name") +
                        outputJson.getString("ext"));
                Metadata metaDataFile = new Metadata();
                metaDataFile.writeXmpFile(convertedFile);
            }

            File convertedFile = new File(inputJson.getString("destPath") + outputJson.getString("name") + "\\" + outputJson.getString("name")
                    + outputJson.getString("ext"));

            Metadata metaDataFile = new Metadata();
            metaDataFile.writeXmpFile(convertedFile);
            FolderZipped.zipFolder(inputJson.getString("destPath") + outputJson.getString("name"));
            return new VideoToAudioResponse(fileName, fileDownloadUri, asset.getContentType(), asset.getSize(), configJson.getString("newFormat"),
                    configJson.getString("audioCodec"), configJson.getString("audioBitRate"), configJson.getString("audioChannel"), configJson.getString("audioSampleRate"), outputChecksumString);
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

}
