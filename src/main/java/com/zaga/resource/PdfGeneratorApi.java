package com.zaga.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.zaga.model.WeeklyTimesheet;
import com.zaga.service.PdfFromHtml;

import io.quarkus.qute.TemplateInstance;

@Path("/Zaga/document-service")
public class PdfGeneratorApi {
    @Inject
    PdfFromHtml service;

    @GET
    @Path("/{amount}")
    public Response generatePdf(@PathParam("amount") String amount) {
        return service.qoteTemplateGenerate(amount);
    }

    @POST
    @Path("/createTimesheet")
    public TemplateInstance generateTimesheetPdf(WeeklyTimesheet weeklyTimesheet) {
        return service.TimesheetTemplateGenerate(weeklyTimesheet);
    }
}
