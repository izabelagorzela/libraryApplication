package org.gorzela.library.server.controller;

import org.gorzela.library.common.Alias;
import org.gorzela.library.server.repository.AliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Rest/alias")
public class AliasController  {

    @Autowired
    AliasRepository aliasRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Alias>> getAllAlias(){
        ArrayList<Alias> list = aliasRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getAliasById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alias> getAliasById(Long id) {
        Alias alias = aliasRepository.findOne(id);
        if(alias != null) {
            return ResponseEntity.status(HttpStatus.OK).body(alias);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
