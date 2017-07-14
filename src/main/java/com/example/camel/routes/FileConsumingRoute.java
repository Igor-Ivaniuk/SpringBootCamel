package com.example.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
public class FileConsumingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        String fileEndpointURI = "file://{{fileRoute.source}}" // Input folder
                + "?autoCreate=true" // attempts to create input folder if it is missing
                + "&preMove=.processing" // move input file to this folder BEFORE start of processing
                + "&move=../.done" // move input file to this folder AFTER successful processing
                + "&moveFailed=../.error" // move input file to this folder if error happened. If there is no error folder, file will stay in input and Camel will try to process it again
                + "&include=.*.xml" // Regex include filter
                + ""
                ;
        // File endpoint has many other options, including retry policies, duplicates detection etc. Check this: http://camel.apache.org/file2.html
        from(fileEndpointURI)
                .to("stream:out");
    }
}
