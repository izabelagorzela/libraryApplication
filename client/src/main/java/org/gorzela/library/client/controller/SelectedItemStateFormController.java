package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gorzela.library.client.Data.LibraryData;
import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@FXMLController
public class SelectedItemStateFormController extends AbstractFormController {

    @FXML
    private TableView<Book> selectedStateTable;

    @FXML
    private TableView<Loan> selectedBookLoanTable;

    @FXML
    private TableView<Reservation> selectedBookReservationTable;

    @FXML
    private TableColumn<Reservation, String> reservationDateToColumn;

    @FXML
    private TableColumn<Reservation, String> reservationDateFromColumn;

    @FXML
    private TableColumn<Loan, String> loanDateToColumn;

    @FXML
    private TableColumn<Loan, String> loanDateFromColumn;

    @FXML
    private TableColumn<Book, String> selectedStateTableAliasColumn;

    @FXML
    private TableColumn<Book, String> selectedStateTableTitleColumn;

    @FXML
    private TableColumn<Book, String> selectedStateTableAuthorColumn;

    @FXML
    private Button reservationButton;

    @FXML
    private Button closeSelectedItemStateButton;

    private ObservableList<Book> selectedBookData;

    private ObservableList<Loan> selectedBookLoanData;

    private ObservableList<Reservation> selectedBookReservationData;

    @Autowired
    private LibraryData libraryData;

    @FXML
    public void closeSelectedItemStateAction(ActionEvent event) {

        closeWindow((Button)event.getSource());
    }

    public void setBookData(Book bookDateTab[]) {

        List<Book> bookList = Arrays.asList(bookDateTab);
        selectedBookData = FXCollections.observableArrayList(bookList);

    }

    public void setSelectedBookLoanData(Loan loanDataTab[]) {

        List<Loan> loanList = Arrays.asList(loanDataTab);
        selectedBookLoanData = FXCollections.observableArrayList(loanList);

    }

    public void setSelectedBookReservationData(Reservation reservationDataTab[]) {

        List<Reservation> ReservationList = Arrays.asList(reservationDataTab);
        selectedBookReservationData = FXCollections.observableArrayList(ReservationList);

    }

    public boolean setSelectedStateTable() throws URISyntaxException {

        if(libraryData.setSelectedBook() == true) {

            selectedStateTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("AuthorsNames"));
            selectedStateTableAliasColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("AliasesNames"));
            selectedStateTableTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            selectedStateTable.setItems(selectedBookData);
            return true;
        }
        return false;
    }

    public boolean setSelectedBookLoanTable() throws URISyntaxException {

        if(libraryData.setLoanData() == true) {

            loanDateFromColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("FormatDateFrom"));
            loanDateToColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("FormatDateTo"));
            selectedBookLoanTable.setItems(selectedBookLoanData);
            if(selectedBookLoanData == null || selectedBookLoanData.size() == 0) {
                selectedBookLoanTable.setPlaceholder(new Label("Nie znaleziono"));
            }
            return true;
        }
        return false;

    }

    public boolean setSelectedBookReservationTable() throws URISyntaxException {

        if(libraryData.setReservationData() == true) {

            reservationDateFromColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("FormatDateFrom"));
            reservationDateToColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("FormatDateTo"));
            selectedBookReservationTable.setItems(selectedBookReservationData);
            return true;
        }

        return false;
    }

    @FXML
    public void reservationAction(ActionEvent event) {

    }

    @Override
    public boolean setWindow() throws URISyntaxException{
        if (setSelectedStateTable() == false)
            return false;
        if(setSelectedBookLoanTable() == false)
            return false;
        if(setSelectedBookReservationTable() == false)
            return false;
        return true;
    }
}
