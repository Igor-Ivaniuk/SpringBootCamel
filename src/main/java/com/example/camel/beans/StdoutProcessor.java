package com.example.camel.beans;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
@Slf4j
public class StdoutProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class); //automatic type convert
        log.info("In StdoutProcessor");
        log.info(body);
    }
}
