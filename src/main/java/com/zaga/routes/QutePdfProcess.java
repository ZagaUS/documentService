package com.zaga.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

public class QutePdfProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String amount = exchange.getIn().getHeader("amount", String.class);


        // Set the dynamic values in the data map
        Map<String, Object> data = new HashMap<>();
        data.put("amount", amount);


        // Render the template using the dynamic values
        String rendered = exchange.getContext().createProducerTemplate()
                .requestBody("qute:Quote.qute", data, String.class);

        // Set the rendered template as the output body
        exchange.getIn().setBody(rendered);
    }
}
