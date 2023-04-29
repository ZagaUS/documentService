package com.zaga.routes;

import com.itextpdf.html2pdf.HtmlConverter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HtmlToPdfProcess implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        String html = exchange.getIn().getBody(String.class);
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, pdfOutput);
        InputStream pdfInput = new ByteArrayInputStream(pdfOutput.toByteArray());
        exchange.getIn().setBody(pdfInput);
    }
}
