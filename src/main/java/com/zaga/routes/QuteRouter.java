package com.zaga.routes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.itextpdf.html2pdf.HtmlConverter;

@ApplicationScoped
public class QuteRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {

    //     restConfiguration()
    //     .component("restlet")
    //     .bindingMode(RestBindingMode.json)
    //     .dataFormatProperty("prettyPrint", "true")
    //     .port("8080");

    // rest("/pdf")
    //     .produces(MediaType.MULTIPART_FORM_DATA)
    //     .post()
    //     .to("direct:convertToPdf");



        from("file:src/main/resources/templates?noop=true")
          .routeId("convertToPdf")
          .setHeader("amount", constant(200))
          .log("----------------${header.amount}")
          .setBody(constant("{\"amount\": 100}"))
          .log("${body}")
          .process(new QutePdfProcess())                        // process 1 Rendering Template with dynamic values
          .log("${body}")
           .process(new HtmlToPdfProcess())                     // process 2 Converts Html to Pdf  
         .log("${body}")
         .process(new GoogleDriveProcess())                     // Process 3 saves the Pdf to Google Drive
         .to("google-drive://drive-files/insert?clientId=705119118954-f2bfftdqq4is5oj113td0cs96sdhdetq.apps.googleusercontent.com&clientSecret=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&accessToken=ya29.a0Ael9sCPjSSM6VrdxNqIo5zPv_sBgKqunL4r543wAaTJBkU7AV2ECXYPrGxPHhoQ4T--k2ZnCcNb6Q3-TjLCEssFyENOKkMkYGUiJmRHpiQ4wYK-60pTD1coOY38zibi7zK8t49ax0KCXvjcaV6_l56JlPduesg_IaCgYKAR8SARMSFQF4udJhtuHwLwTfktITYAYGsCa19A0167&refreshToken=1//04pz5wMjsUxp2CgYIARAAGAQSNwF-L9IrEDgIPrTHw2JjQ3YZuodWEM2jqoSnYoV8D3bN4_XTtYTMp3PpSwCNLRloc8QaSh0oM_s");
    
    }
    
}
