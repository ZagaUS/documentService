package com.zaga.resource;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.Produce;

import com.zaga.model.Invoice;
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
    // @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateTimesheetPdf(WeeklyTimesheet weeklyTimesheet) {
        System.out.println(weeklyTimesheet);
        return service.TimesheetTemplateGenerate(weeklyTimesheet);
    }

    @POST
    @Path("/createInvoice")
    public Response generateinvoicePdf(Invoice invoice) throws IOException {
        return service.invoiceTemplateGenerate(invoice);
    }
}
