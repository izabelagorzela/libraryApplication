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
import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.client.Data.ReaderData;
import org.gorzela.library.client.security.CurrentReaderProvider;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@FXMLController
public class ReaderFormController extends AbstractFormController {

    private ObservableList<Reservation> reservationData;

    private ObservableList<Loan> loanData;

    @Autowired
    private ReaderData readerData;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    @FXML
    private TableView<Reservation> readerReservationTableView;

    @FXML
    private TableView<Loan> rentalsTableView;

    @FXML
    private TableColumn<Reservation, String> reservationTableDateFromColumn;

    @FXML
    private TableColumn<Reservation, String> reservationTableDateToColumn;

    @FXML
    private TableColumn<Reservation, String> reservationTableTitleColumn;

    @FXML
    private TableColumn<Reservation, String> reservationTableAuthorColumn;

    @FXML
    private TableColumn<Loan, String> rentalsTableAuthorColumn;

    @FXML
    private TableColumn<Loan, String> rentalsTableTitleColumn;

    @FXML
    private TableColumn<Loan, String> rentalsTableDateFromColumn;

    @FXML
    private TableColumn<Loan, String> rentalsTableDateToColumn;

    @FXML
    private Button cancelReservationButton;

    @FXML
    private Button closeReaderAccountButton;

    @FXML
    private Button prolongButton;

    @FXML
    private Label readerPaymentLabel;

    public void setReservationData(Reservation reservationDateTab[]) {

        List<Reservation> reservationList = Arrays.asList(reservationDateTab);
        reservationData = FXCollections.observableArrayList(reservationList);
        if(reservationData.size() == 0) {

            readerReservationTableView.setPlaceholder(new Label("Nie posiadasz aktualnych rezerwacji"));
            cancelReservationButton.setDisable(true);
        }

    }

    public void setLoanData(Loan loanDateTab[]) {

        List<Loan> loanList = Arrays.asList(loanDateTab);
        loanData = FXCollections.observableArrayList(loanList);
        if(loanData.size() == 0) {

            rentalsTableView.setPlaceholder(new Label("Nie posiadzasz aktualnych wypożyczeń"));
            prolongButton.setDisable(true);
        }

    }

    @FXML
    public void closeReaderAccountAction(ActionEvent event) throws URISyntaxException {

        closeWindow((Button)event.getSource());

    }

    @FXML
    public void prolongBookAction(ActionEvent event) throws URISyntaxException {

        readerData.updateLoanData(rentalsTableView.getSelectionModel().getSelectedItem());
        setRentalsTable();
    }

    @FXML
    void cancelReservationOnAction(ActionEvent event)throws URISyntaxException {

        readerData.cancelReservation(readerReservationTableView.getSelectionModel().getSelectedItem());
        setReservationTable();

    }

    public boolean setReservationTable() throws URISyntaxException {

        if(readerData.setReservationData() == true) {

            reservationTableDateFromColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("formatDateFrom"));
            reservationTableDateToColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("formatDateTo"));
            reservationTableTitleColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("titleName"));
            reservationTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("authorName"));
            readerReservationTableView.setItems(reservationData);
            return true;
        }
        return false;
    }

    public boolean setRentalsTable() throws URISyntaxException {

        if(readerData.setLoanData() == true) {

            rentalsTableDateFromColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("formatDateFrom"));
            rentalsTableDateToColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("formatDateTo"));
            rentalsTableTitleColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("titleName"));
            rentalsTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("authorName"));
            rentalsTableView.setItems(loanData);
            return true;
        }
        return false;
    }



    @Override
    public boolean setWindow() throws URISyntaxException {

        if (setReservationTable() == false)
            return false;
        if (setRentalsTable() == false)
            return false;
            readerPaymentLabel.setText(String.valueOf(currentReaderProvider.getCurrentReader().getPayment()));
            return true;

    }

}


