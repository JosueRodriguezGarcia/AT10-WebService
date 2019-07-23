package com.fundation.webservice.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ConvertPPTtoPdf {
    public static void main(String[] args) throws IOException, DocumentException {

        //load any ppt file
        FileInputStream inputStream = new FileInputStream("D:\\prueba\\OBSERVER.ppt");//("C:\\Users\\LimbertVargas\\Desktop\\OBSERVER.ppt");
        SlideShow ppt = new SlideShow(inputStream);
        inputStream.close();
        Dimension pgsize = ppt.getPageSize();

        //take first slide and save it as an image
        Slide slide = ppt.getSlides()[0];
        BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint(Color.white);
        graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
                pgsize.height));
        slide.draw(graphics);
        FileOutputStream out = new FileOutputStream("D:\\prueba\\OBSERVERslide.png");//("C:\\Users\\LimbertVargas\\Desktop\\OBSERVERslide.png");
        javax.imageio.ImageIO.write(img, "png", out);
        out.close();

        //get saved slide-image and save it into pdf
        Image slideImage = Image.getInstance("D:\\prueba\\OBSERVERslide.png");//("C:\\Users\\LimbertVargas\\Desktop\\OBSERVERslide.png");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\prueba\\OBSERVERpdf.pdf"));//("C:\\Users\\LimbertVargas\\Desktop\\OBSERVERpdf.pdf"));
        document.setPageSize(new Rectangle(slideImage.getWidth(), slideImage.getHeight()));
        document.open();
        slideImage.setAbsolutePosition(0, 0);
        document.add(slideImage);
        document.close();

    }
}
