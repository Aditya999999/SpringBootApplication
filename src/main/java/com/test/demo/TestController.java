package com.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {


    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    private BookRepository repository;
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        List<Book> books = (List<Book>) repository.findAll();

        return books;
    }

    @GetMapping("/book")
    public Optional<Book> getByBookId(@RequestParam(value = "Id") Long Id){
        Optional<Book> book = repository.findById(Id);

        return book;
    }

    @PostMapping("/book")

    public Book addBook(@RequestParam (value = "name") String bookName, @RequestParam (value = "desc") String description){
        Book book = new Book(bookName, description);

        repository.save(book);
        return book;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
