package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

@FXMLController
public class SearchResultFormController {

    @FXML
    private TableView<?> searchResultTableView;

    @FXML
    private Button closeSearchResultFormButton;

    @FXML
    private TableColumn<?, ?> searchResultTableAuthorColumn;

    @FXML
    private TableColumn<?, ?> searchResultTableAliasColumn;

    @FXML
    private TableColumn<?, ?> searchResultTableDateFromColumn;

    @FXML
    private Button reservationButton;

    @FXML
    private TableColumn<?, ?> searchResultTableStateColumn;

    @FXML
    private TableColumn<?, ?> searchResultTableDateToColumn;

    @FXML
    private TableColumn<?, ?> searchResultTableTitleColumn;

    @FXML
    void reserveAction(ActionEvent event) {

    }

    @FXML
    void closeSearchResultFormAction(ActionEvent event) {

        Stage stage = (Stage) closeSearchResultFormButton.getScene().getWindow();
        stage.close();

    }
}
