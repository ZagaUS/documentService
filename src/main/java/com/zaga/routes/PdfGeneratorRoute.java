package com.zaga.routes;

import org.apache.camel.builder.RouteBuilder;

//@ApplicationScoped
public class PdfGeneratorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:src/main/resources/nofolder")
                .routeId("store-to-google-drive")
                .setHeader("CamelGoogleDrive.content-type", constant("application/pdf"))
                .process(new PdfProcess())
                .to("google-drive://drive-files/insert?clientId=705119118954-f2bfftdqq4is5oj113td0cs96sdhdetq.apps.googleusercontent.com&clientSecret=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&accessToken=ya29.a0Ael9sCPjSSM6VrdxNqIo5zPv_sBgKqunL4r543wAaTJBkU7AV2ECXYPrGxPHhoQ4T--k2ZnCcNb6Q3-TjLCEssFyENOKkMkYGUiJmRHpiQ4wYK-60pTD1coOY38zibi7zK8t49ax0KCXvjcaV6_l56JlPduesg_IaCgYKAR8SARMSFQF4udJhtuHwLwTfktITYAYGsCa19A0167&refreshToken=1//04pz5wMjsUxp2CgYIARAAGAQSNwF-L9IrEDgIPrTHw2JjQ3YZuodWEM2jqoSnYoV8D3bN4_XTtYTMp3PpSwCNLRloc8QaSh0oM_s");

    }

}
