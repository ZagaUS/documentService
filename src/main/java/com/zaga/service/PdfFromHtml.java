package com.zaga.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang3.ArrayUtils;

import com.itextpdf.html2pdf.HtmlConverter;
// import com.itextpdf.text.pdf.PdfDocument;
// import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.zaga.model.WeeklyTimesheet;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
public class PdfFromHtml {

    @Location("Quote.html")
    Template quoteTemplate;

    @Location("Timesheet.html")
    Template timesheetTemplate;

    @Location("InvoiceOne.html")
    Template invoiceOneTemplate;
    @Location("InvoiceTwo.html")
    Template invoiceTwoTemplate;

    public Response qoteTemplateGenerate(String amount) {

        String html = quoteTemplate.data("amount", amount).render();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream os = byteArrayOutputStream;
        HtmlConverter.convertToPdf(html, os);

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return Response.ok(pdfBytes).build();

    }

    public Response TimesheetTemplateGenerate(WeeklyTimesheet weekly) {

        System.out.println("--------------" + weekly);
        Map<String, Object> data = new HashMap<>();
        data.put("weekly", weekly);

        String html = timesheetTemplate.data(data).render();

        // OutputStream os = new FileOutputStream(
        // "C:/Users/jeyar/OneDrive/Desktop/PdfGenerator/document-service-snapshot-1/documentService/src/main/resources/pdf/TimesheetPdf.pdf");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream os = byteArrayOutputStream;
        HtmlConverter.convertToPdf(html, os);
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        // File pdfFile = new File("example.pdf");
        // byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());
        return Response.ok(pdfBytes).build();

    }

    public Response invoiceTemplateGenerate() throws IOException {

        // System.out.println("--------------" + weekly);
        // Map<String, Object> data = new HashMap<>();
        // data.put("weekly", weekly);

        String htmlOne = invoiceOneTemplate.render();
        String htmlTwo = invoiceTwoTemplate.render();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Convert the first HTML template to PDF
        PdfDocument pdfDoc1 = new PdfDocument(new PdfWriter(outputStream));
        HtmlConverter.convertToPdf(htmlOne, pdfDoc1, null);
        pdfDoc1.close();

        // Convert the second HTML template to PDF
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        PdfDocument pdfDoc2 = new PdfDocument(new PdfWriter(outputStream2));
        HtmlConverter.convertToPdf(htmlTwo, pdfDoc2, null);
        pdfDoc2.close();

        // Merge the two PDF documents into a single file
        ByteArrayOutputStream mergedOutput = new ByteArrayOutputStream();
        PdfDocument mergedPdfDoc = new PdfDocument(new PdfWriter(mergedOutput));
        PdfMerger merger = new PdfMerger(mergedPdfDoc);
        PdfDocument pdfDoc1copy = new PdfDocument(new PdfReader(new ByteArrayInputStream(outputStream.toByteArray())));
        merger.merge(pdfDoc1copy, 1, pdfDoc1copy.getNumberOfPages());
        PdfDocument pdfDoc2copy = new PdfDocument(new PdfReader(new ByteArrayInputStream(outputStream2.toByteArray())));
        merger.merge(pdfDoc2copy, 1, pdfDoc2copy.getNumberOfPages());
        pdfDoc1copy.close();
        pdfDoc2copy.close();
        merger.close();
        mergedPdfDoc.close();

        // Return the merged PDF document bytes in a JAX-RS Response object
        byte[] pdfBytes = mergedOutput.toByteArray();
        return Response.ok(pdfBytes)
                .header("Content-Disposition", "attachment; filename=invoices.pdf")
                .build();

    }

}
