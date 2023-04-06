package com.zaga.routes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.api.client.http.ByteArrayContent;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GoogleDriveProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String folderId = ConfigProvider.getConfig().getValue("GoogleDriveProcess.folderId", String.class);

        // Configuring the Input File Parameters of google drive components

        ParentReference parRef = new ParentReference();
        parRef.setId(folderId);
        List<ParentReference> parents = new ArrayList<>();
        parents.add(parRef);

        InputStream inputStream = exchange.getIn().getBody(InputStream.class);

        File fileMetadata = new File();
        fileMetadata.setTitle("testFile.pdf");
        fileMetadata.setParents(parents);

        byte[] bytes = inputStream.readAllBytes();

        // Passing parameters to the Exchange Headers

        ByteArrayContent mediaContent = new ByteArrayContent("application/pdf", bytes);
        final Map<String, Object> headers = new HashMap<>();

        String clientId = ConfigProvider.getConfig().getValue("GoogleDriveProcess.clientId", String.class);
        String clientSecret = ConfigProvider.getConfig().getValue("GoogleDriveProcess.clientSecret", String.class);
        String acessToken = ConfigProvider.getConfig().getValue("GoogleDriveProcess.accessToken", String.class);
        String refreshToken = ConfigProvider.getConfig().getValue("GoogleDriveProcess.refreshToken", String.class);

        headers.put("CamelGoogleDrive.content", fileMetadata);
        headers.put("CamelGoogleDrive.mediaContent", mediaContent);
        headers.put("clientId", clientId);
        headers.put("clientSecret", clientSecret);
        headers.put("accessToken", acessToken);
        headers.put("refreshToken", refreshToken);

        exchange.getIn().setHeaders(headers);

    }

}
