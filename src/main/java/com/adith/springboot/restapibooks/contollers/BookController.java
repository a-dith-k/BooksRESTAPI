package com.adith.springboot.restapibooks.contollers;

import com.adith.springboot.restapibooks.entities.Book;
import com.adith.springboot.restapibooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookService bookservice;

    //list all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book>list=bookservice.getAllBooks();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
//            return ResponseEntity.of(Optional.of(list));
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }

    }

    //get one particular Book Handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id")int id){
        Book book=bookservice.getBookById(id);
        if(book==null){
            System.out.println("no books");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }

    }

    //add  book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b=null;
        try {
            b=this.bookservice.addBook(book);
            System.out.println(book);
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
            System.out.println("book removed");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/books/{id}")
    public void updateBook(@RequestBody Book book,@PathVariable("id") int id){
        try{
            bookservice.updateBook(book,id);
            System.out.println("book updated");
            ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
