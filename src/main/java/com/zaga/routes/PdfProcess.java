package com.zaga.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

public class PdfProcess implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        

        ParentReference parRef=new ParentReference();
        parRef.setId("11i4BkUqEqBoInkrxG_Ywq5iUX3qALKhw");
        List<ParentReference> parents=new ArrayList<>();
        parents.add(parRef);
        
        java.io.File file=new java.io.File("C:/Users/jeyar/OneDrive/Desktop/PdfGenerator/document-service-snapshot-1/documentService/src/main/resources/pdf/quotePdf.pdf");
       
        
        File fileMetadata = new File();
        fileMetadata.setTitle(file.getName());
        fileMetadata.setParents(parents);

        FileContent mediaContent = new FileContent(null, file);
        final Map<String, Object> headers = new HashMap<>();

        headers.put("CamelGoogleDrive.content", fileMetadata);
        headers.put("CamelGoogleDrive.mediaContent", mediaContent);
        headers.put("clientId","705119118954-f2bfftdqq4is5oj113td0cs96sdhdetq.apps.googleusercontent.com");
        headers.put("clientSecret","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");
        headers.put("accessToken","ya29.a0Ael9sCPjSSM6VrdxNqIo5zPv_sBgKqunL4r543wAaTJBkU7AV2ECXYPrGxPHhoQ4T--k2ZnCcNb6Q3-TjLCEssFyENOKkMkYGUiJmRHpiQ4wYK-60pTD1coOY38zibi7zK8t49ax0KCXvjcaV6_l56JlPduesg_IaCgYKAR8SARMSFQF4udJhtuHwLwTfktITYAYGsCa19A0167");
        headers.put("refreshToken","1//04pz5wMjsUxp2CgYIARAAGAQSNwF-L9IrEDgIPrTHw2JjQ3YZuodWEM2jqoSnYoV8D3bN4_XTtYTMp3PpSwCNLRloc8QaSh0oM_s");
       
        exchange.getIn().setHeaders(headers);

    
    

    }
    
}
