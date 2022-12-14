package com.example.challengen13.utils;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ArabicLigaturizer;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.springframework.core.io.Resource;

public class PdfGenerator {

    public void generate(UserInfoDto userInfo, HttpServletResponse response) throws Exception {
        var rectangle = new Rectangle(625f,404f);
        Document document = new Document(PageSize.A4);
        document.setPageSize(rectangle);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(10);
        String arStr = new String("البطاقة المهنية".getBytes(), "UTF-8");
        Paragraph paragraph1 = new Paragraph(arStr, fontTiltle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);
        Paragraph paragraph2 = new Paragraph("CARTE PROFESSIONNELLE", fontTiltle);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph2);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3});
        table.setSpacingBefore(3);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(3);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(userInfo.getUserImage()));
        BufferedImage qrImqge= generateQRCodeImage(userInfo.getFirstName()+", "+userInfo.getLastName()+", "+userInfo.getCin()+", "+userInfo.getBirthDate());
        table.addCell(Image.getInstance(simpleResizeImage(img),null));
        table.addCell("Prénom: "+userInfo.getFirstName());
        table.addCell(" ");
        table.addCell(userInfo.getCin());
        table.addCell("Nom: "+userInfo.getLastName());
        table.addCell(Image.getInstance(qrImqge,null));


        document.add(table);
        document.close();

    }
    BufferedImage simpleResizeImage(BufferedImage img) {
        return Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 96, 96);
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
            barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
