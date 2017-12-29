package org.gorzela.library.server.repository;

import org.gorzela.library.common.Reader;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Created by Iza on 2017-07-24.
 */
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    List<Reader> findAll();
    Reader findByPassword(String password);
    Reader findByPasswordAndLogin(String password, String login);

}
