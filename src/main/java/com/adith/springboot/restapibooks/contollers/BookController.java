package com.adith.springboot.restapibooks.contollers;

import com.adith.springboot.restapibooks.entities.Book;
import com.adith.springboot.restapibooks.services.BookService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {
    BookService bookservice;
    BookController(BookService bookService){
        this.bookservice=bookService;
    }


    //list all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book>list=bookservice.getAllBooks();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }

    }

    //get one particular Book Handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id")int id){
        Book book=bookservice.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }

    }

    //add  book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        System.out.println(book);
        try {
            this.bookservice.addBook(book);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        try {
            bookservice.removeBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @DeleteMapping("/books")
    public ResponseEntity<Void> deleteAllBook(){
        bookservice.deleteAllBooks();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/books/{id}")
    public void updateBook(@RequestBody Book book,@PathVariable("id") int id){
        try{
            bookservice.updateBook(book,id);
            ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
