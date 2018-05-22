package org.gorzela.library.client.security;

import javafx.event.ActionEvent;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.common.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class LoginData {

    @Autowired
    private LibraryRestTemplateFactory builderFactory;

    @Autowired
    private LibraryUriComponentsFactory uriFactory;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;


    public void login(ActionEvent event, Pair loginPair) throws IOException, URISyntaxException {

        currentReaderProvider.increaseCurrentReaderSetupAccount();

        if (loginPair.getLogin().equals("") == true || loginPair.getPassword().equals("") == true) {

            alertInformation.showInformation("Brak danych", "Nie podałeś loginu bądź hasła");
            currentReaderProvider.checkCurrentReaderSetupAccount(event);

        } else {

            ResponseEntity<Reader> entity = builderFactory.getRestTemplate(loginPair.getLogin(), loginPair.getPassword()).getForEntity(uriFactory.getUri("/reader/get/one"), Reader.class);
            if (entity.getStatusCode() == HttpStatus.OK) {

                currentReaderProvider.afterLogin(event, entity.getBody());

            } else {

                currentReaderProvider.checkCurrentReaderSetupAccount(event);
            }
        }


    }
}


