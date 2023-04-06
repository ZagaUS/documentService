package com.zaga.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

//@ApplicationScoped
public class QuteRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // restConfiguration()
    // .component("restlet")
    // .bindingMode(RestBindingMode.json)
    // .dataFormatProperty("prettyPrint", "true")
    // .port("8080");

    // rest("/pdf")
    // .produces(MediaType.MULTIPART_FORM_DATA)
    // .post()
    // .to("direct:convertToPdf");

    from("file:src/main/resources/nofolder?noop=true")
        .routeId("convertToPdf")
        .setHeader("amount", constant(200))
        .log("----------------${header.amount}")
        .setBody(constant("{\"amount\": 100}"))
        .log("${body}")
        .process(new QutePdfProcess()) // process 1 Rendering Template with dynamic values
        .log("${body}")
        .process(new HtmlToPdfProcess()) // process 2 Converts Html to Pdf
        .log("${body}")
        .process(new GoogleDriveProcess()) // Process 3 saves the Pdf to Google Drive
        .to("google-drive://drive-files/insert?clientId=${header.clientId}&clientSecret=${header.clientSecret}&accessToken=${header.accessToken}&refreshToken=${header.refreshToken}");

  }

}
