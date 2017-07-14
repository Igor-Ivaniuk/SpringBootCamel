package com.example.camel.routes;

import com.example.camel.beans.ParsedXmlObjectBean;
import com.example.camel.beans.StdoutProcessor;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
public class FileRoutes extends RouteBuilder {

    // You can autowire your beans or processors in the route definition
    @Autowired
    private StdoutProcessor stdoutProcessor;

    @Override
    public void configure() throws Exception {

        String inputFileEndpointURI = "file://{{fileRoute.source}}" // Input folder
                + "?autoCreate=true" // attempts to create input folder if it is missing
                + "&preMove=.processing" // move input file to this folder BEFORE start of processing
                + "&move=.done/${file:onlyname}.${date:now:yyyMMdd_HHmmss}" // move input file to this folder AFTER successful processing, and append timestamp to the name
                + "&moveFailed=../.error" // move input file to this folder if error happened. If there is no error folder, file will stay in input and Camel will try to process it again
                + "&include=.*.xml" // Regex include filter
                // File endpoint has many other options, including retry policies, duplicates detection etc. Check this: http://camel.apache.org/file2.html
                ;

        String outputFileEndpointURI = "file://{{fileRoute.dest}}" // Output folder
                + "?fileName=${header.OriginalFileName}.processed.${date:now:yyyMMdd_HHmmss}" // Output filename
                ;

        // File reader route
        from(inputFileEndpointURI)
                .routeId("FileReader")
                .setHeader("OriginalFileName", ExpressionBuilder.headerExpression("CamelFileName")) // Camel sets a lot of headers that travel together with Exchange. We populate custom header to reuse it in beans
                .process(stdoutProcessor)
                .bean(ParsedXmlObjectBean.class)
                .to("stream:out") // One more log after author replacement
                .to("direct://fileWriter") //One route can have multiple outputs, they will be called in sequence by default
                .to("seda://databaseWriter")
        // direct endpoint directly calls other route within same Camel context
        // seda endpoint creates an in-memory queue for non-blocking calls, also within one Camel Context
        ;

        // File writer route
        from("direct://fileWriter")
                .to(outputFileEndpointURI);
    }
}
