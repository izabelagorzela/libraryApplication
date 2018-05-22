package org.gorzela.library.client.security;

import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.client.controller.LoginFormController;
import org.gorzela.library.client.util.AlertInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class SecureResponseErrorHandler implements ResponseErrorHandler {

    @Autowired
    private LoginFormController loginFormController;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus code = response.getStatusCode();
        if(HttpStatus.OK != code) {
            currentReaderProvider.setReaderAllowed(false);
            return true;
        }

        currentReaderProvider.setReaderAllowed(true);
        return false;
    }

   public void handleError(ClientHttpResponse response) throws IOException {

       HttpStatus code = response.getStatusCode();
       if(code == HttpStatus.UNAUTHORIZED) {

           alertInformation.showInformation("Niepoprawne dane uwierzytelniające", "Podałeś błędne hasło bądź login");
           loginFormController.resetLoginTextFields();
       }
       if(code == HttpStatus.FORBIDDEN) {

           alertInformation.showInformation("Brak dostępu", "Nie masz uprawnień");
           loginFormController.resetLoginTextFields();
       }
    }
}
