package org.gorzela.library.server.controller;


import lombok.Setter;
import org.gorzela.library.common.Reader;
import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Iza on 2017-07-24.
 */

@Setter
@RestController
@RequestMapping("/Rest/reader")
public class ReaderController {

    @Autowired
    ReaderRepository readerRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reader>> getAllReader(){
        List<Reader> list = readerRepository.findAll();
        //System.out.println("call method");
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> getReaderByPassword(@PathVariable String password) {
        Reader reader = readerRepository.findByPassword(password);
        if(reader != null) {
            return ResponseEntity.status(HttpStatus.OK).body(reader);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> getReaderByPasswordAndLogin(String password, String login) {
        Reader reader = readerRepository.findByPasswordAndLogin(password, login);
        if(reader != null) {
            return ResponseEntity.status(HttpStatus.OK).body(reader);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
