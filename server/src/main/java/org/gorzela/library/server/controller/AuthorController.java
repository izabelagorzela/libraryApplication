package org.gorzela.library.server.controller;


import lombok.Setter;
import org.gorzela.library.common.Alias;
import org.gorzela.library.common.Author;
import org.gorzela.library.common.Book;
import org.gorzela.library.server.repository.AliasRepository;
import org.gorzela.library.server.repository.AuthorRepository;
import org.gorzela.library.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Setter
@RestController
@RequestMapping("/Rest/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Author>> getAllAuthor(){
        ArrayList<Author> list = authorRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getAuthorById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getAuthorById(Long id) {
        Author author = authorRepository.findOne(id);
        if(author != null) {
            return ResponseEntity.status(HttpStatus.OK).body(author);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping(value = "/getAuthorByAliasPart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Author>> getAuthorByAliasPart(String partLastName) {
        ArrayList<Author> list = authorRepository.findByAliases_LastNameContaining(partLastName);
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/addNewAuthor")
    public ResponseEntity<String> addAuthor(Long idAuthor, String firstName, String secondName) {
        Author author = new Author(idAuthor, firstName, secondName, null, null);
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body("added");
    }
}
