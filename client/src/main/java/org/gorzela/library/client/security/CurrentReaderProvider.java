package org.gorzela.library.client.security;

import javafx.event.ActionEvent;
import lombok.*;
import org.gorzela.library.client.controller.LoginFormController;
import org.gorzela.library.client.controller.ReaderFormController;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.client.view.LoginFormView;
import org.gorzela.library.client.view.ReaderFormView;
import org.gorzela.library.common.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CurrentReaderProvider {

    @Autowired
    CurrentWindow currentWindow;

    @Autowired
    private LoginFormView loginWindow;

    @Autowired
    private ReaderFormView readerWindow;

    @Autowired
    private LoginFormController loginFormController;

    @Autowired
    private ReaderFormController readerFormController;

    @Autowired
    private AlertInformation alertInformation;

    private Reader currentReader;

    private int currentReaderSetupAccount;

    @Setter
    @Getter
    private boolean isReaderAllowed;

    public Reader getCurrentReader() {

        if(currentReader == null) {

            loginFormController.createAndShowWindow(loginWindow, loginFormController, "Logowanie");
        }
        return currentReader;
    }

    public void unsetCurrentReader() {

        currentReader = null;
    }

    public void resetCurrentReaderSetupAccount(){

        setCurrentReaderSetupAccount(0);
    }

    public void increaseCurrentReaderSetupAccount() {

        currentReaderSetupAccount += 1;
    }

    public void checkCurrentReaderSetupAccount(ActionEvent event) {

        if (currentReaderSetupAccount == 3) {

            alertInformation.showInformation("Błąd", "Próba trzykrotnego logowania nie powiodła się");
            loginFormController.closeLoginWindow(event, "Logowanie nie powiodło się", "");
            resetCurrentReaderSetupAccount();
        }
    }

    public void afterLogin(ActionEvent event, Reader currentReader) {

        setCurrentReader(currentReader);
        loginFormController.resetLoginTextFields();
        loginFormController.closeLoginWindow(event, "Jesteś zalogowany jako:", currentReader.getLogin(), false);
        if(currentWindow.getWindowName().equals("readerWindow") == true ) {
            readerFormController.createAndShowWindow(readerWindow, readerFormController, "Twoje konto");
        }
        resetCurrentReaderSetupAccount();
    }
}
