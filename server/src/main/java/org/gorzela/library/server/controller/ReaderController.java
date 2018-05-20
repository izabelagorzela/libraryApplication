package org.gorzela.library.server.controller;


import javafx.collections.ObservableList;
import lombok.Setter;
import org.gorzela.library.common.Reader;
import org.gorzela.library.common.ReaderLoansDTO;
import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("get/one")                                                    //WYKORZYSTANA
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

    //z DTO nie dziala jeszcze
    @GetMapping("get/loans")
    public ResponseEntity<List<ReaderLoansDTO>> getReaderLoans(Long readerId) {

        List<ReaderLoansDTO> readerLoans = readerRepository.getReaderLoans(readerId);
        if(readerLoans == null || readerLoans.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(readerLoans);
    }

//    @GetMapping(value = "/getPaymentByReaderId", produces = MediaType.APPLICATION_JSON_VALUE)            //WYKORZYSTANA
//    public ResponseEntity<Payment> getByReaderId(Long readerId) {
//        Payment payment = paymentRepository.findByReaderId(readerId);
//        if (payment != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(payment);
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
//    }
}
