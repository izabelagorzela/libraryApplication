package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

@FXMLController
public class StatisticsFormController {

    @FXML
    private TableView<?> statisticsTableView;

    @FXML
    private Button closeStatisticsFormButton;

    @FXML
    private TableColumn<?, ?> statisticsTableAuthorColumn;

    @FXML
    private TableColumn<?, ?> statisticsTableTitleColumn;

    @FXML
    void closeStatisticsFormAction(ActionEvent event) {

        Stage stage = (Stage) closeStatisticsFormButton.getScene().getWindow();
        stage.close();

    }

}
