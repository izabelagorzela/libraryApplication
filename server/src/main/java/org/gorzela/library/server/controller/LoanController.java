package org.gorzela.library.server.controller;

import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reader;
import org.gorzela.library.server.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/Rest/library/loan")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @GetMapping("get/one")
    public ResponseEntity<Loan> getLoanById(Long loanId){
        Loan loan = loanRepository.findOne(loanId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("get/byReaderId")
    public ResponseEntity<ArrayList<Loan>> getByReaderId(Long readerId) {

        ArrayList<Loan> loans = loanRepository.getByReaderId(readerId);
        if(loans != null || loans.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(loans);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

//    @GetMapping("get/byReaderIdAndReturnDate")                                                          //zle
//    public ResponseEntity<ArrayList<Loan>> getByReaderIdAndReturnDate(Long readerId, @RequestParam(name = "returnDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date returnDate) {
//
//        ArrayList<Loan> loans = loanRepository.getByReaderIdAndReturnDate(readerId, returnDate);
//        if(loans != null || loans.size() > 0) {
//            return ResponseEntity.status(HttpStatus.OK).body(loans);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
//    }

    @GetMapping(value = "get/byReaderAndReturnDate")                                                          //uzywana
    public ResponseEntity<ArrayList<Loan>> getByReaderAndReturnDate(Long readerId) {

        Reader reader = new Reader();
        reader.setReaderId(readerId);
        ArrayList<Loan> loans = loanRepository.findByReaderAndReturnDateIsNull(reader);
        if(loans != null || loans.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(loans);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping(value = "get/byBookAndReturnDate")                                                          //uzywana
    public ResponseEntity<ArrayList<Loan>> getByBookAndReturnDate(Long bookId) {

        Book book = new Book();
        book.setBookId(bookId);
        ArrayList<Loan> loans = loanRepository.findByBookAndReturnDateIsNull(book);
        if(loans != null || loans.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(loans);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }



    //jest używana

    @Transactional
    @PutMapping("update/one")
    public ResponseEntity<String> setNewDateToById(@RequestParam(name = "newDateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date newDateTo, Long loanId){
        Integer modifiedRows = loanRepository.setNewDateTo(newDateTo, loanId);
        if(modifiedRows == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("updated");
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("not update");
        }
    }




//    @Transactional
//    @DeleteMapping("delete/one")
//    public ResponseEntity<Loan> addLoan(Reader reader, Book book) {
//
//        Loan loan = new Loan();
//        loan.setReader(reader);
//
//
//        return ResponseEntity.ok(loan);
//    }
}



