package com.zaga.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.itextpdf.html2pdf.HtmlConverter;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
public class PdfFromHtml {
    
@Location("Quotation.html")
Template quoteTemplate;


public TemplateInstance qoteTemplateGenerate(String amount){
    
    String html = quoteTemplate.data("amount",amount).render();
    try {
        OutputStream os = new FileOutputStream("C:/Users/jeyar/OneDrive/Desktop/PdfGenerator/document-service-snapshot-1/documentService/src/main/resources/pdf/quotePdf.pdf");
        HtmlConverter.convertToPdf(html, os);
    } catch (FileNotFoundException e) {
       
        e.printStackTrace();
    }
      return quoteTemplate.data("amount",amount);

}

}
