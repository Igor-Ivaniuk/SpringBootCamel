package com.example.camel.beans;

import com.example.types.BookForm;
import com.example.types.BooksForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by igori on 14.07.2017.
 */
@Component
@Slf4j
public class ParsedXmlObjectBean {

    // Camel will convert the XML string to POJO for us
    public BooksForm processParsedXML(BooksForm booksForm) {
        BookForm firstBook = booksForm.getBook().get(0);
        log.info("Book author: " + firstBook.getAuthor());
        firstBook.setAuthor("Igor Ivaniuk");
        booksForm.getBook().add(firstBook);
        return booksForm;
    }
}
