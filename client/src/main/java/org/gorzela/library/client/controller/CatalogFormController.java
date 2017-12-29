package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gorzela.library.client.view.SearchResultFormView;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class CatalogFormController implements Initializable {
    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> phraseKindComboBox;

    @FXML
    private Button closeCatalogFormButton;

    @FXML
    private TextField phraseTextField;

    @Autowired
    private SearchResultFormView searchResultWindow;

    @FXML
    void openSearchResultFormAction(ActionEvent event) {

        Stage stage = new Stage();
        Scene scene = (searchResultWindow.getView().getScene() == null ? new Scene(searchResultWindow.getView()) : searchResultWindow.getView().getScene());
        stage.setScene(scene);
        stage.setTitle("Wyniki wyszukiwania");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void closeCatalogFormAction(ActionEvent event) {

        Stage stage = (Stage) closeCatalogFormButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phraseKindComboBox.getItems().removeAll(phraseKindComboBox.getItems());
        phraseKindComboBox.getItems().addAll("Tytuł", "Autor", "ISBN");
        phraseKindComboBox.getSelectionModel().select("Tytuł");
    }
}
