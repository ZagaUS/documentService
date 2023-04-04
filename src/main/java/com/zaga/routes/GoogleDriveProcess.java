package com.zaga.routes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.api.client.http.ByteArrayContent;
//import com.google.api.client.util.IOUtils;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;
//import com.itextpdf.layout.element.List;


public class GoogleDriveProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ParentReference parRef = new ParentReference();
parRef.setId("11i4BkUqEqBoInkrxG_Ywq5iUX3qALKhw");
List<ParentReference> parents = new ArrayList<>();
parents.add(parRef);

InputStream inputStream = exchange.getIn().getBody(InputStream.class);

File fileMetadata = new File();
fileMetadata.setTitle("myFile.pdf");
fileMetadata.setParents(parents);
//InputStream inputStream = ...; // get your input stream from somewhere
byte[] bytes = inputStream.readAllBytes();

ByteArrayContent mediaContent = new ByteArrayContent("application/pdf", bytes);
final Map<String, Object> headers = new HashMap<>();
// headers.put(GoogleDriveConstants.PROPERTY_CONTENT, fileMetadata);
// headers.put(GoogleDriveConstants.PROPERTY_MEDIA_CONTENT, mediaContent);
// exchange.getIn().setHeaders(headers);


headers.put("CamelGoogleDrive.content", fileMetadata);
headers.put("CamelGoogleDrive.mediaContent", mediaContent);
exchange.getIn().setHeaders(headers);

    }
    
}
