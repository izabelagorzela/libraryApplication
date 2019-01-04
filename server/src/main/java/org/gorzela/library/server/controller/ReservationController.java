package org.gorzela.library.server.controller;


import org.gorzela.library.common.Book;
import org.gorzela.library.common.Reader;
import org.gorzela.library.common.Reservation;
import org.gorzela.library.server.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/Rest/library/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Reservation>> getAllReservation(){
        ArrayList<Reservation> list = reservationRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @Transactional
    @DeleteMapping(value = "/deleteById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(Long id){

        Integer deletedRows = reservationRepository.deleteByReservationId(id);
        if(deletedRows == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("not removed");
        }
    }



    @PostMapping(value = "/addNewReservation")
    public ResponseEntity<Reservation> addReservation(@RequestParam(name = "dateFrom")@DateTimeFormat(pattern = "yyyy-MM-dd")Date dateFrom , @RequestParam(name = "dateTo")@DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo, Long readerId, Long bookId){
        Reservation reservation = new Reservation();
        Book book = new Book();
        book.setBookId(bookId);
        Reader reader = new Reader();
        reader.setReaderId(readerId);

        reservation.setDateFrom(dateFrom);
        reservation.setDateTo(dateTo);
        reservation.setReader(reader);
        reservation.setBook(book);
        reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("get/byReaderId")
    public ResponseEntity<Reservation[]> getByReaderId(Long readerId) {

        ArrayList<Reservation> reservations = reservationRepository.getByReaderId(readerId);
        Reservation[] reservationTable = reservations.toArray(new Reservation[reservations.size()]);
        if(reservationTable != null || reservationTable.length > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(reservationTable);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

    }

    @GetMapping(value = "get/byBookAndCancelDate")
    public ResponseEntity<ArrayList<Reservation>> getByBookAndReturnDate(Long bookId) {

        Book book = new Book();
        book.setBookId(bookId);
        ArrayList<Reservation> reservationList = reservationRepository.findByBookAndCancelDateIsNull(book);
        if(reservationList != null || reservationList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(reservationList);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
