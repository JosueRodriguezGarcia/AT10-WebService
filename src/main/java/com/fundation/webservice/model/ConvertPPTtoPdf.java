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

}

    public void convertPPTXtoImgtoPDF() throws IOException, DocumentException, InvalidFormatException {
        FileInputStream inputStream = new FileInputStream("vzw.pptx");

        XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(inputStream));

        inputStream.close();
        Dimension pgsize = ppt.getPageSize();
        float scale = 1;
        int width = (int) (pgsize.width * scale);
        int height = (int) (pgsize.height * scale);

        int i = 1;
        int totalSlides = ppt.getSlides().size();

        for (XSLFSlide slide : ppt.getSlides()) {

            BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
                    pgsize.height));
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setColor(Color.white);
            graphics.clearRect(0, 0, width, height);
            graphics.scale(scale, scale);

            slide.draw(graphics);
            FileOutputStream out = new FileOutputStream("images/" + i + ".png");
            javax.imageio.ImageIO.write(img, "png", out);
            out.close();
            i++;
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("filenew.pdf"));
        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(1);


        for (int j = 1; j <= totalSlides; j++) {
            Image slideImage = Image.getInstance("images/" + j + ".png");

            document.setPageSize(new Rectangle(slideImage.getWidth(), slideImage.getHeight()));
            document.open();
            slideImage.setAbsolutePosition(0, 0);

            table.addCell(new com.lowagie.text.pdf.PdfPCell(slideImage, true));

        }
        document.add(table);
        document.close();
    }
}