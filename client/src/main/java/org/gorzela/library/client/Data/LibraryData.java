package org.gorzela.library.client.Data;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.client.controller.CatalogFormController;
import org.gorzela.library.client.controller.SearchResultFormController;
import org.gorzela.library.client.controller.SelectedItemStateFormController;
import org.gorzela.library.client.security.LibraryRestTemplateFactory;
import org.gorzela.library.client.security.LibraryUriComponentsFactory;
import org.gorzela.library.client.util.BookSearchTrio;
import org.gorzela.library.client.util.ErrorInformation;
import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class LibraryData {

    @Autowired
    private LibraryRestTemplateFactory restTemplateFactory;

    @Autowired
    private BookSearchTrio bookSearchTrio;

    @Autowired
    private LibraryUriComponentsFactory uriFactory;

    @Autowired
    private ErrorInformation errorInformation;

    @Autowired
    private SearchResultFormController searchResultFormController;

    @Autowired
    private SelectedItemStateFormController selectedItemStateFormController;

    public LibraryData() {
    }

    public ObservableList<Book> getStatisticsData() throws URISyntaxException {

        Book[] forNow = restTemplateFactory.getRestTemplate().getForObject(uriFactory.getUri("/book/getMostLoaned"), Book[].class);
        List<Book> bookList = Arrays.asList(forNow);
        return FXCollections.observableArrayList(bookList);

    }


    public boolean setBookDataByTitlePart(String titlePart) throws URISyntaxException {

        ResponseEntity<Book[]> entity;
        URI uri = uriFactory.getUri("/book/getBookByTitlePart", "titlePart", titlePart);
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
                entity = restTemplate.getForEntity(uri, Book[].class);

        } catch (Exception ex) {

                log.error("Something wrong happened...");
                return false;
        }
        if (entity.getStatusCode() == HttpStatus.OK) {

            Book[] forNow = entity.getBody();
            searchResultFormController.setBookData(forNow);
            return true;
        }
        else {
                return false;
            }
        }

    public boolean setBookDataIsbnPart(String isbnPart) throws URISyntaxException {

        ResponseEntity<Book[]> entity;
        URI uri = uriFactory.getUri("/book/getBookByIsbnPart", "isbnPart", isbnPart);
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
            entity = restTemplate.getForEntity(uri, Book[].class);

        } catch (Exception ex) {

            log.error("Something wrong happened...");
            return false;
        }
        if (entity.getStatusCode() == HttpStatus.OK) {

            Book[] forNow = entity.getBody();
            searchResultFormController.setBookData(forNow);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setBookDataAuthorPart(String partLastName) throws URISyntaxException {

        ResponseEntity<Book[]> entity;
        URI uri = uriFactory.getUri("/book/getBookByAuthorPart", "partLastName", partLastName);
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
            entity = restTemplate.getForEntity(uri, Book[].class);

        } catch (Exception ex) {

            log.error("Something wrong happened...");
            return false;
        }
        if (entity.getStatusCode() == HttpStatus.OK) {

            Book[] forNow = entity.getBody();
            searchResultFormController.setBookData(forNow);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setSelectedBook() throws URISyntaxException {


        ResponseEntity<Book> entity;
        URI uri = uriFactory.getUri("/book/getBookById", "id", bookSearchTrio.getSelectedBookId().toString());
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
            entity = restTemplate.getForEntity(uri, Book.class);

        } catch (Exception ex) {
            log.error("Something wrong happened...");
            return false;
        }
        if (entity.getStatusCode() == HttpStatus.OK) {

            Book bookForNow = entity.getBody();
            Book[] forNow = new Book[1];
            forNow[0] = bookForNow;
            selectedItemStateFormController.setBookData(forNow);
            return true;
        } else {
            return false;
        }

    }

    public boolean setLoanData() throws URISyntaxException {

        ResponseEntity<Loan[]> entity;
        URI uri = uriFactory.getUri("/loan/get/byBookAndReturnDate", "bookId", bookSearchTrio.getSelectedBookId().toString());
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
            entity = restTemplate.getForEntity(uri, Loan[].class);
        } catch (Exception ex) {

            log.error("Something wrong happened...");
            return false;
        }

        if (entity.getStatusCode() == HttpStatus.OK) {
            Loan[] forNow = entity.getBody();
            selectedItemStateFormController.setSelectedBookLoanData(forNow);
            return true;
        }

        return false;
    }

    public boolean setReservationData() throws URISyntaxException {

        ResponseEntity<Reservation[]> entity;
        URI uri = uriFactory.getUri("/reservation/get/byBookAndCancelDate", "bookId", bookSearchTrio.getSelectedBookId().toString());
        RestTemplate restTemplate = restTemplateFactory.getRestTemplate();
        try {
            entity = restTemplate.getForEntity(uri, Reservation[].class);
        } catch (Exception ex) {

            log.error("Something wrong happened...");
            return false;
        }

        if (entity.getStatusCode() == HttpStatus.OK) {
            Reservation[] forNow = entity.getBody();
            selectedItemStateFormController.setSelectedBookReservationData(forNow);
            return true;
        }

        return false;
    }

}
