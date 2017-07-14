package com.example.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


/**
 * Created by IgorIvaniuk on 13.07.2017.
 */

@Component
public class SayHiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")
                .routeId("supermegaroute") // Need to set route ID to be able to control it through Camel Context
                .autoStartup(false)
                .transform(method("myBean", "saySomething")) // You can reference the bean by name in context
                .to("stream:out");
    }
}
