package org.gorzela.library.server.repository;

import org.gorzela.library.common.Reader;
import org.gorzela.library.common.ReaderLoansDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;

/**
 * Created by Iza on 2017-07-24.
 */

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    ArrayList<Reader> findAll();
    Reader findByLogin(String login);

    @Query("select new org.gorzela.library.common.ReaderLoansDTO(r, l, b) from Reader r, Loan l, Book b where l.reader.id = :readerId and l.reader.id = r.id and l.book.id = b.id")
    ArrayList<ReaderLoansDTO> getReaderLoans(@Param("readerId") Long readerId);

}
