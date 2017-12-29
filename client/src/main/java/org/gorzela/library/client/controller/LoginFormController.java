package org.gorzela.library.client.controller;


import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@FXMLController
public class LoginFormController {

    @FXML
    private Button readerAccountButton;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField loginTextField;

    @FXML
    void closeLoginFormAction(ActionEvent event) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signIn(ActionEvent event) {

        //dopisac kod logowania, pobranie danych z resta

    }

}
