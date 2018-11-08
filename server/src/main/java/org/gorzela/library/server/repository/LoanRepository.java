package org.gorzela.library.server.repository;

import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reader;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;
import java.util.Date;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    @Query("select l from Loan l where l.reader.id = :readerId")          //operujemy na obiektach i ich polach
    ArrayList<Loan> getByReaderId(@Param("readerId") Long readerId);

//    @Query("select l from Loan l where l.reader.id = ?1 and l.returnDate=?2")          //operujemy na obiektach i ich polach
//    ArrayList<Loan> getByReaderIdAndReturnDate(Long readerId, Date returnDate);

    ArrayList<Loan> findByReaderAndReturnDateIsNull(Reader reader);

    ArrayList<Loan> findByBookAndReturnDateIsNull(Book book);


    @Modifying
    @Query("update Loan l set l.dateTo = ?1 where l.loanId = ?2")
    int setNewDateTo(Date newDateTo, Long loanId);

    ArrayList<Loan> findByDateToBeforeAndReturnDateIsNull(Date date);

}
