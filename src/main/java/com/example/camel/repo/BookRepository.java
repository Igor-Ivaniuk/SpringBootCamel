package com.example.camel.repo;

import com.example.camel.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by igori on 14.07.2017.
 */
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
