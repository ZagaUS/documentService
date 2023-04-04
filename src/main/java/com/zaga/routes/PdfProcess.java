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
        exchange.getIn().setHeaders(headers);

    
    

    }
    
}
