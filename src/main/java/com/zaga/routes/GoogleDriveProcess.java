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
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
//import com.itextpdf.layout.element.List;

@ApplicationScoped
public class GoogleDriveProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String folderId = ConfigProvider.getConfig().getValue("GoogleDriveProcess.folderId", String.class);

        // Configring the Input File Parameters of google drive components

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
        String acessToke = ConfigProvider.getConfig().getValue("GoogleDriveProcess.accessToken", String.class);
        String refreshToken = ConfigProvider.getConfig().getValue("GoogleDriveProcess.refreshToken", String.class);

        headers.put("CamelGoogleDrive.content", fileMetadata);
        headers.put("CamelGoogleDrive.mediaContent", mediaContent);
        headers.put("clientId", "705119118954-f2bfftdqq4is5oj113td0cs96sdhdetq.apps.googleusercontent.com");
        headers.put("clientSecret", "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");
        headers.put("accessToken",
                "ya29.a0Ael9sCPjSSM6VrdxNqIo5zPv_sBgKqunL4r543wAaTJBkU7AV2ECXYPrGxPHhoQ4T--k2ZnCcNb6Q3-TjLCEssFyENOKkMkYGUiJmRHpiQ4wYK-60pTD1coOY38zibi7zK8t49ax0KCXvjcaV6_l56JlPduesg_IaCgYKAR8SARMSFQF4udJhtuHwLwTfktITYAYGsCa19A0167");
        headers.put("refreshToken",
                "1//04pz5wMjsUxp2CgYIARAAGAQSNwF-L9IrEDgIPrTHw2JjQ3YZuodWEM2jqoSnYoV8D3bN4_XTtYTMp3PpSwCNLRloc8QaSh0oM_s");

        exchange.getIn().setHeaders(headers);

    }

}
