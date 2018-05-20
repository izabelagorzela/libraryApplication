package org.gorzela.library.server.controller;

import lombok.Setter;
import org.gorzela.library.common.Book;
import org.gorzela.library.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Setter
@RestController
@RequestMapping("/Rest/library/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> getAllAuthor(){
        ArrayList<Book> list = bookRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getBookById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookById(Long id) {
        Book book = bookRepository.findOne(id);
        if(book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping(value = "/getMostLoaned", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> getMostLoaned() {
        ArrayList<Book> books = bookRepository.getMostLoaned(new PageRequest(0, 3));
        if(books != null || books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping(value = "/getBookByTitlePart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> getBookByTitlePart(String titlePart) {
        ArrayList<Book> list = bookRepository.findByTitleContaining(titlePart);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getBookByIsbnPart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> getBookByIsbnPart(String isbnPart) {
        ArrayList<Book> list = bookRepository.findByIsbnContaining(isbnPart);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getBookByAuthorPart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> getAuthorByAliasPart(String partLastName) {
        ArrayList<Book> list = bookRepository.findByAuthors_LastNameContaining(partLastName);
        return ResponseEntity.ok(list);
    }
}

