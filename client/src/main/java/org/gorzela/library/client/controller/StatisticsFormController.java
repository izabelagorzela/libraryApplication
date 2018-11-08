package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gorzela.library.client.Data.LibraryData;
import org.gorzela.library.common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URISyntaxException;

@FXMLController
public class StatisticsFormController extends AbstractFormController {

    @Autowired
    private LibraryData libraryData;

    @FXML
    private TableView<Book> statisticsTableView;

    @FXML
    private TableColumn<Book, String> statisticsTableAuthorColumn;

    @FXML
    private TableColumn<Book, String> statisticsTableTitleColumn;

    @FXML
    private Button closeStatisticsFormButton;

    @FXML
    public void closeStatisticsFormAction(ActionEvent event) {

        closeWindow((Button)event.getSource());
    }

    public void setStatisticsTable() throws URISyntaxException {

        statisticsTableTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        statisticsTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("authorsNames"));
        statisticsTableView.setItems(libraryData.getStatisticsData());
    }

    @Override
    public boolean setWindow() throws URISyntaxException {

        setStatisticsTable();
        return true;

    }

}


