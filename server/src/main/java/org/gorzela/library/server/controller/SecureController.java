package org.gorzela.library.server.controller;

import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Rest/reader")
public class SecureController {

    @Autowired
    ReaderRepository readerRepository;


}
