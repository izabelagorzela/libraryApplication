package org.gorzela.library.server.repository;

import org.gorzela.library.common.Alias;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface AliasRepository extends CrudRepository<Alias, Long> {

    ArrayList<Alias> findAll();

}
