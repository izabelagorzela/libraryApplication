package org.gorzela.library.client.Data;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.client.controller.ReaderFormController;
import org.gorzela.library.client.security.CurrentReaderProvider;
import org.gorzela.library.client.security.LibraryRestTemplateFactory;
import org.gorzela.library.client.security.LibraryUriComponentsFactory;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.client.util.LibraryDate;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class ReaderData {

    @Autowired
    private LibraryRestTemplateFactory restTemplateFactory;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private LibraryUriComponentsFactory uriFactory;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    @Autowired
    private ReaderFormController readerFormController;

    @Autowired
    LibraryDate libraryDate;


    public boolean setReservationData() throws URISyntaxException {

        ResponseEntity<Reservation[]> entity;
        URI uri = uriFactory.getUri("/reservation/get/byReaderId", "readerId", currentReaderProvider.getCurrentReader().getReaderId().toString());
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate(currentReaderProvider.getCurrentReader().getLogin(), currentReaderProvider.getCurrentReader().getPassword());
        try {
            entity = restTemplate.getForEntity(uri, Reservation[].class);
        } catch (Exception ex) {
            if (currentReaderProvider.isReaderAllowed()) {
                // tell that something is wrong
                log.error("Something wrong happened...");
            }

            return false;
        }

        if (entity.getStatusCode() == HttpStatus.OK) {
            Reservation[] forNow = entity.getBody();
            readerFormController.setReservationData(forNow);
            return true;
        }

        return false;

    }

    public boolean setLoanData() throws URISyntaxException {

        ResponseEntity<Loan[]> entity;
        URI uri = uriFactory.getUri("/loan/get/byReaderAndReturnDate", "readerId", currentReaderProvider.getCurrentReader().getReaderId().toString());
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate(currentReaderProvider.getCurrentReader().getLogin(), currentReaderProvider.getCurrentReader().getPassword());
        try {
            entity = restTemplate.getForEntity(uri, Loan[].class);
        } catch (Exception ex) {
            if (currentReaderProvider.isReaderAllowed()) {
                // tell that something is wrong
                log.error("Something wrong happened...");
            }

            return false;
        }

        if (entity.getStatusCode() == HttpStatus.OK) {
            Loan[] forNow = entity.getBody();
            readerFormController.setLoanData(forNow);
            return true;
        }

        return false;
    }

    public void updateLoanData(Loan selectedLoan) throws URISyntaxException {

        ResponseEntity<String> entity;
        if (selectedLoan == null) {

            alertInformation.showInformation("Błąd", "Nie wybrałeś żadnej pozycji do prolongaty...");
        } else {
            URI uri = uriFactory.getUri("/loan/update/one", "newDateTo", libraryDate.getProlongedDateAsString(), "loanId", selectedLoan.getLoanId().toString());
            RestTemplate restTemplate = restTemplateFactory.getRestTemplate(currentReaderProvider.getCurrentReader().getLogin(), currentReaderProvider.getCurrentReader().getPassword());

            try {

                entity = restTemplate.exchange(uri, HttpMethod.PUT, null, String.class);
                if (entity.getStatusCode() == HttpStatus.NO_CONTENT) {

                    alertInformation.showInformation("Błąd", "To wypożyczenie już nie jest aktualna...nie można go prolongować");
                }

            } catch (Exception ex) {

                log.error("Something wrong happened...");
                Platform.exit();

            }
        }
    }

    public void cancelReservation(Reservation selectedReservation) throws URISyntaxException {

        ResponseEntity<String> entity;
        if (selectedReservation == null) {

            alertInformation.showInformation("Błąd", "Nie wybrałeś żadnej rezerwacji do usunięcia...");
            //alertInformation.showConfirm("Potwierdzenie", "Czy chcesz usunąć wybraną rezerwację...");
        }
        else {

            URI uri = uriFactory.getUri("/reservation/deleteById", "id", selectedReservation.getReservationId().toString());
            RestTemplate restTemplate = restTemplateFactory.getRestTemplate(currentReaderProvider.getCurrentReader().getLogin(), currentReaderProvider.getCurrentReader().getPassword());

            try {

                entity = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
                if (entity.getStatusCode() == HttpStatus.NO_CONTENT) {

                   alertInformation.showInformation("Błąd", "Ta rezerwacja już nie jest aktualna...nie można jej usunąć");
                }

            } catch (Exception ex) {

                log.error("Something wrong happened...");
                Platform.exit();

            }
        }

    }
}
