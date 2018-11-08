package org.gorzela.library.client.controller;


import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.gorzela.library.client.security.CurrentReaderProvider;
import org.gorzela.library.client.security.LoginData;
import org.gorzela.library.client.security.Pair;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.client.view.ReaderFormView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
@FXMLController
public class LoginFormController extends AbstractFormController {

    @Autowired
    private ReaderFormView readerWindow;

    @Autowired
    private MainFormController mainFormController;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private Pair loginPair;

    @Autowired
    private LoginData loginData;

    @FXML
    private Button signInButton;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField loginTextField;

    @FXML
    public void closeLoginFormAction(ActionEvent event) {

        closeLoginWindow(event, "Wstrzymano logowanie", "");
        currentReaderProvider.resetCurrentReaderSetupAccount();
    }

    @FXML
    public void signIn(ActionEvent event)throws IOException, URISyntaxException{

        loginPair.setLogin(loginTextField.getText());
        loginPair.setPassword(passwordPasswordField.getText());
        loginData.login(event, loginPair);
    }

    public void closeLoginWindow(ActionEvent e, String info1, String info2) {

        closeWindow((Button)e.getSource());
        mainFormController.setLabels(info1, info2);
    }

    public void closeLoginWindow(ActionEvent e, String info1, String info2, Boolean buttonState) {

        closeWindow((Button)e.getSource());
        mainFormController.setLabels(info1, info2);
        mainFormController.setSignOffButton(buttonState);
    }

    public void resetLoginTextFields() {

        loginTextField.setText("");
        passwordPasswordField.setText("");
    }

    @Override
    public boolean setWindow(){

        return true;
    }

}






