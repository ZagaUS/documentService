package com.zaga.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.zaga.service.PdfFromHtml;

import io.quarkus.qute.TemplateInstance;

@Path("/Zaga/document-service")
public class PdfGeneratorApi {
    @Inject
    PdfFromHtml service ;
    
    @GET
    @Path("/{amount}")
    public TemplateInstance generatePdf(@PathParam("amount") String amount){
        return  service.qoteTemplateGenerate(amount);
    }
}
