package org.gorzela.library.client.controller;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import de.felixroske.jfxsupport.FXMLController;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import org.apache.http.client.utils.URIBuilder;
import org.gorzela.library.client.security.CurrentReaderProvider;
import org.gorzela.library.client.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@FXMLController
public class MainFormController {

    @FXML
    private Button catalogButton;

    @FXML
    private Button loginFormButton;

    @FXML
    private Button ourStatisticsButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Label informationLabel;

    @FXML
    private Label currentReaderLabel;

    @FXML
    private MenuItem closeApplicationMenu;

    //@Autowired
   //private LoginFormView loginWindow;

    @Autowired
    private StatisticsFormView statisticsWindow;

    @Autowired
    private CatalogFormView catalogWindow;

    @Autowired
    private ReaderFormView readerWindow;

    @Autowired
    private CurrentReaderProvider currentReaderProvider;

    @FXML
    void closeApplicationAction(ActionEvent event) {

            Platform.exit();

    }

    @FXML
    void openReaderAccountForm(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = (readerWindow.getView().getScene() == null ? new Scene(readerWindow.getView()) : readerWindow.getView().getScene());
        stage.setScene(scene);
        stage.setTitle("Twoje konto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    @FXML
    void openStatisticsForm(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = (statisticsWindow.getView().getScene() == null ? new Scene(statisticsWindow.getView()) : statisticsWindow.getView().getScene());
        stage.setScene(scene);
        stage.setTitle("Nasze statystyki");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    @FXML
    void openCatalogForm(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = (catalogWindow.getView().getScene() == null ? new Scene(catalogWindow.getView()) : catalogWindow.getView().getScene());
        stage.setScene(scene);
        stage.setTitle("Katalog on-line");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }





}


