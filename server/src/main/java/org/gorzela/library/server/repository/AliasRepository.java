package org.gorzela.library.server.repository;

import org.gorzela.library.common.Alias;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface AliasRepository extends CrudRepository<Alias, Long> {

    ArrayList<Alias> findAll();

}
