package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.client.util.SelectedBook;
import org.gorzela.library.client.view.SearchResultFormView;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class CatalogFormController extends AbstractFormController implements Initializable{

    @Autowired
    private SelectedBook selectedBook;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private SearchResultFormView searchResultWindow;

    @Autowired
    private SearchResultFormController searchResultFormController;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> phraseKindComboBox;

    @FXML
    private Button closeCatalogFormButton;

    @FXML
    private TextField phraseTextField;

    @FXML
    public void closeCatalogFormAction(ActionEvent event) {

        closeWindow((Button)event.getSource());
    }

    @FXML
    public void openSearchResultFormAction(ActionEvent event) {

        if (phraseTextField.getText().equals("") == true) {

            alertInformation.showInformation("Błąd", "Nie podałeś frazy według której ma być wykonane wyszukiwanie... ");
        }
        else {
            selectedBook.setPhrasePart(phraseTextField.getText());
            selectedBook.setPhraseType(phraseKindComboBox.getSelectionModel().getSelectedItem());
            createAndShowWindow(searchResultWindow, searchResultFormController, "Wyniki wyszukiwania");
            resetPhraseTextFields();
        }
    }

    public void resetPhraseTextFields() {

        phraseTextField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phraseKindComboBox.getItems().removeAll(phraseKindComboBox.getItems());
        phraseKindComboBox.getItems().addAll("Tytuł", "Autor", "ISBN");
        phraseKindComboBox.getSelectionModel().select("Tytuł");
    }

    @Override
    public boolean setWindow(){

        return true;
    }
}
