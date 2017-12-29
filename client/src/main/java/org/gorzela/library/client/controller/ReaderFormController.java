package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

@FXMLController
public class ReaderFormController {

        @FXML
        private TableView<?> readerReservationTableView;

        @FXML
        private TableColumn<?, ?> reservationTableDateFromColumn;

        @FXML
        private Button cancelReservationButton;

        @FXML
        private TableView<?> rentalsTableView;

        @FXML
        private Button closeReaderAccountButton;

        @FXML
        private TableColumn<?, ?> rentalsTableAuthorColumn;

        @FXML
        private Button prolongButton;

        @FXML
        private TableColumn<?, ?> reservationTableDateToColumn;

        @FXML
        private TableColumn<?, ?> rentalsTableTitleColumn;

        @FXML
        private Label readerPaymentLabel;

        @FXML
        private TableColumn<?, ?> rentalsTableDateFromColumn;

        @FXML
        private TableColumn<?, ?> reservationTableTitleColumn;

        @FXML
        private TableColumn<?, ?> reservationTableAuthorColumn;

        @FXML
        private TableColumn<?, ?> rentalsTableDateToColumn;

        @FXML
        void closeReaderAccountAction(ActionEvent event) {

            Stage stage = (Stage) closeReaderAccountButton.getScene().getWindow();
            stage.close();
        }

    }


