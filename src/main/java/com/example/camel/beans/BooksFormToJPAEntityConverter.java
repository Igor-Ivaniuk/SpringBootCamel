package com.example.camel.beans;

import com.example.camel.model.BookEntity;
import com.example.types.BookForm;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
public class BooksFormToJPAEntityConverter implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        BookForm book = exchange.getIn().getBody(BookForm.class);
        BookEntity entity = new BookEntity();
        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setBookId(book.getId());
        //Store the JPA entity in body
        exchange.getIn().setBody(entity);
    }
}
