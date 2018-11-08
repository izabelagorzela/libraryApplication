package org.gorzela.library.server.repository;

import org.gorzela.library.common.Author;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    ArrayList<Author> findAll();

    ArrayList<Author> findByAliases_LastNameContaining(String partLastName);

}
