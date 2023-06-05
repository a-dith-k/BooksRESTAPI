package com.adith.springboot.restapibooks.services;

import com.adith.springboot.restapibooks.entities.Book;
import com.adith.springboot.restapibooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

//    private static  List<Book> list=new ArrayList<>();
//
//    static{
//        list.add(new Book(1,"Core Java","Adith"));
//        list.add(new Book(2,"Hello Java","Hari"));
//        list.add(new Book(3,"Robust Java","Jhons"));
//        list.add(new Book(4,"Think Java","Dibin"));
//    }

//    getallbooks
    public List<Book> getAllBooks(){
//        Iterable<Book>itr= bookRepository.findAll();
        //findAll returns a iterable we will typecast it into list

//        List<Book>books=(List<Book>)bookRepository.findAll();
        return (List<Book>)bookRepository.findAll();

    }

    //get book by id
    public Book getBookById(int id){

        Book book=null;
        try {
            book=bookRepository.findBookById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

         return book;

    }
    //adding the book
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    //remove book
    public void removeBook(int id){
        bookRepository.deleteById(id);
    }

    //update book
    public void updateBook(Book book, int id){


       book.setId(id);
       bookRepository.save(book);
    }
}
