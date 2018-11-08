package org.gorzela.library.server.repository;

import org.gorzela.library.common.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book, Long> {

    ArrayList<Book> findAll();

    @Query("select l.book from Loan l group by l.book.id order by count(l) desc, l.book.title asc")
    ArrayList<Book> getMostLoaned(Pageable pageable);

    ArrayList<Book> findByTitleContaining(String titlePart);

    ArrayList<Book> findByIsbnContaining(String isbnPart);

    ArrayList<Book> findByAuthors_LastNameContaining(String partLastName);

}
