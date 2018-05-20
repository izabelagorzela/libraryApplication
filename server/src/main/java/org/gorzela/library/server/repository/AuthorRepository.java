package org.gorzela.library.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.gorzela.library.common.Author;

import java.util.ArrayList;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    ArrayList<Author> findAll();

    ArrayList<Author> findByAliases_LastNameContaining(String partLastName);

}
