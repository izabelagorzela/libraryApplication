package org.gorzela.library.server.repository;


import org.gorzela.library.common.Book;
import org.gorzela.library.common.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    ArrayList<Reservation> findAll();

    @Query("select r from Reservation r where r.reader.id = :readerId")
    ArrayList<Reservation> getByReaderId(@Param("readerId") Long readerId);

    @Modifying
    @Query("delete from Reservation r where r.reservationId = ?1")
    int deleteByReservationId(long reservationId);

    ArrayList<Reservation> findByBookAndCancelDateIsNull(Book book);
}
