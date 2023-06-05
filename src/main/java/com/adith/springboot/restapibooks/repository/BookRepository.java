package com.adith.springboot.restapibooks.repository;

import com.adith.springboot.restapibooks.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {

    Book findBookById(int id);
}
