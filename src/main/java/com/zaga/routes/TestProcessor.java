package com.zaga.routes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.itextpdf.html2pdf.HtmlConverter;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
public class TestProcessor implements Processor{

    @Location("Quote.html")
    Template quoteTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
       
        
         String amount = "200";
        
            
            String html = quoteTemplate.data("amount",amount).render();
            try {
                OutputStream os = new FileOutputStream("C:/Users/jeyar/OneDrive/Desktop/PdfGenerator/document-service-snapshot-1/documentService/src/main/resources/pdf/quotePdf.pdf");
                HtmlConverter.convertToPdf(html, os);
            } catch (FileNotFoundException e) {
               
                e.printStackTrace();
            }
              quoteTemplate.data("amount",amount);
        
        }
    }
    

