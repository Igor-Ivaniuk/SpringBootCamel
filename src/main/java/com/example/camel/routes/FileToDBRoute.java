package com.example.camel.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
public class FileToDBRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("seda://databaseWriter")
                .to("stream:out");
    }
}
