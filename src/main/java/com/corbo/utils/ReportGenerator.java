package com.corbo.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class ReportGenerator {

    public static void generatePDF(String fileName) throws Exception {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();
        document.add(new Paragraph("Document Processing Report"));
        document.close();
    }
}