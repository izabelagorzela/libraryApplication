package org.gorzela.library.client.controller;


import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.client.Data.ReaderData;
import org.gorzela.library.client.security.CurrentReaderProvider;
import org.gorzela.library.client.security.CurrentWindow;
import org.gorzela.library.client.view.CatalogFormView;
import org.gorzela.library.client.view.ReaderFormView;
import org.gorzela.library.client.view.StatisticsFormView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import org.apache.http.client.utils.URIBuilder;

@Slf4j
@Component
@FXMLController
public class MainFormController extends AbstractFormController {

    @Autowired
    private ReaderData readerData;
    @Autowired
    private CurrentWindow currentWindow;

    @Autowired
    private StatisticsFormView statisticsWindow;

    @Autowired
    private StatisticsFormController statisticsController;

    @Autowired
    private CatalogFormView catalogWindow;

    @Autowired
    private CatalogFormController catalogController;

    @Autowired
    private ReaderFormView readerWindow;

    @Autowired
    private ReaderFormController readerController;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    @FXML
    private Button catalogButton;

    @FXML
    private Button loginFormButton;

    @FXML
    private Button ourStatisticsButton;

    @FXML
    private Button signOffButton;

    @FXML
    private Label informationLabel;

    @FXML
    private Label currentReaderLabel;

    @FXML
    private MenuItem closeApplicationMenu;


    public void setSignOffButton(boolean buttonState) {

        signOffButton.setDisable(buttonState);
    }

    public void setLabels(String text1, String text2) {

        informationLabel.setText(text1);
        currentReaderLabel.setText(text2);
    }

    @FXML
    public void closeApplicationAction(ActionEvent event){

            Platform.exit();
    }

    @FXML
    public void openReaderAccountForm(ActionEvent event) {

        if (currentReaderProvider.getCurrentReader() != null) {

            createAndShowWindow(readerWindow, readerController, "Twoje konto");
        }
        else {
            currentWindow.setWindowName("readerWindow");
        }
    }

    @FXML
    public void openStatisticsForm(ActionEvent event) {

        createAndShowWindow(statisticsWindow, statisticsController,"Nasze statystyki");
    }

    @FXML
    public void openCatalogForm(ActionEvent event) {

        createAndShowWindow(catalogWindow, catalogController,"Katalog on-line");
    }

    @FXML
    public void signOffAction(ActionEvent event) {

        setLabels("Zostałeś wylogowany", "");
        signOffButton.setDisable(true);
        currentReaderProvider.unsetCurrentReader();
    }

    @Override
    public boolean setWindow(){

        return true;
    }

}


