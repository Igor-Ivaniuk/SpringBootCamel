package com.example.camel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by igori on 14.07.2017.
 */
@Entity
@Data
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String bookId;
    private String author;
    private String title;


}
