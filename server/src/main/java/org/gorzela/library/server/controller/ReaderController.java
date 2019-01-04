package org.gorzela.library.server.controller;


import lombok.Setter;
import org.gorzela.library.common.Reader;
import org.gorzela.library.common.ReaderLoansDTO;
import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iza on 2017-07-24.
 */

@Setter
@RestController
@RequestMapping("/Rest/library/reader")
public class ReaderController {

    @Autowired
    ReaderRepository readerRepository;

    @GetMapping("get/one")
    public ResponseEntity<Reader> getReader() {

        Reader reader = readerRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if(reader == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(reader);
    }

    @GetMapping("get/all")
    public ResponseEntity<ArrayList<Reader>> getAllReader(){
        ArrayList<Reader> list = readerRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("get/loans")
    public ResponseEntity<List<ReaderLoansDTO>> getReaderLoans(Long readerId) {

        List<ReaderLoansDTO> readerLoans = readerRepository.getReaderLoans(readerId);
        if(readerLoans == null || readerLoans.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(readerLoans);
    }
}
