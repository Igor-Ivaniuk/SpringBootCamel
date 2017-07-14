package com.example.camel.routes;


import com.example.camel.beans.BooksFormToJPAEntityConverter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
public class DBRoutes extends RouteBuilder {

    @Autowired
    private BooksFormToJPAEntityConverter converter;

    @Override
    public void configure() throws Exception {

        from("seda://databaseWriter")
                .setBody(simple("${body.getBook}")) // We unwrap the POJO and put list of books into body
                .split(body()) // Split the collection and send individual books for separate processing
                .to("direct://processIndividualBooks");

        from("direct://processIndividualBooks")
                .process(converter)
                .to("jpa"); // Persist the individual books
    }
}
