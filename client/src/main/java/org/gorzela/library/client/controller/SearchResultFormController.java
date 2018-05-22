package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gorzela.library.client.Data.LibraryData;
import org.gorzela.library.client.util.SelectedBook;
import org.gorzela.library.client.util.AlertInformation;
import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@FXMLController
public class SearchResultFormController extends AbstractFormController {

    @Autowired
    private LibraryData libraryData;

    @Autowired
    private AlertInformation alertInformation;

    @Autowired
    private SelectedBook selectedBook;

    private ObservableList<Book> foundBookData;

    private boolean settingsLabel = false;

    @FXML
    private TableView<Book> searchResultTableView;

    @FXML
    private TableColumn<Book, String> searchResultTableAuthorColumn;

    @FXML
    private TableColumn<Book, String> searchResultTableAliasColumn;

    @FXML
    private TableColumn<Book, String> searchResultTableCategoryColumn;

    @FXML
    private TableColumn<Book, String> searchResultTableISBNColumn;

    @FXML
    private TableColumn<Book, String> searchResultTablePublishingHouseColumn;

    @FXML
    private TableColumn<Book, String> searchResultTableTitleColumn;

    @FXML
    private Label loanDateToLabel;

    @FXML
    private Label loanDateFromLabel;

    @FXML
    private Label reservationDateFromLabel;

    @FXML
    private Label reservationDateToLabel;

    @FXML
    private Label reservationFromHeaderLabel;

    @FXML
    private Label loanFromHeaderLabel;

    @FXML
    private Label loanToHeaderLabel;

    @FXML
    private Label reservationToHeaderLabel;

    @FXML
    private Label statusInformationLabel;

    @FXML
    private Button closeSearchResultFormButton;

    @FXML
    private Button showSelectedButton;

    @FXML
    private Button reserveButton;

    @FXML
    public void closeSearchResultFormAction(ActionEvent event) {

        closeWindow((Button)event.getSource());

    }

    @FXML
    void reserveAction(ActionEvent event) {

    }

    @FXML
    public void showSelectedBook(ActionEvent event) throws URISyntaxException {

        if (searchResultTableView.getSelectionModel().getSelectedItem() == null) {

            alertInformation.showInformation("Błąd", "Nie wybrałeś żadnej pozycji do wglądu...");

        } else {

            selectedBook.setSelectedBook(searchResultTableView.getSelectionModel().getSelectedItem());         //ustawiamy pozycje
            if (libraryData.setCurrentLoanData() == true) {

                loanDateFromLabel.setText(selectedBook.getSelectedBookCurrentLoan().getFormatDateFrom());
                loanDateToLabel.setText(selectedBook.getSelectedBookCurrentLoan().getFormatDateTo());
                loanFromHeaderLabel.setText("Wypożyczona od");
                loanToHeaderLabel.setText("Wypożyczona do");
                settingsLabel = true;

            }
            if(libraryData.setCurrentReservationData() == true) {

                reservationDateFromLabel.setText(selectedBook.getSelectedBookCurrentReservation().getFormatDateFrom());
                reservationDateToLabel.setText(selectedBook.getSelectedBookCurrentReservation().getFormatDateTo());
                reservationFromHeaderLabel.setText("Zarezerwowana od");
                reservationToHeaderLabel.setText("Zarezerwowana do");
                if(settingsLabel != true) {

                    settingsLabel = true;
                }

            }
            if(settingsLabel == false) {

                statusInformationLabel.setText("Ta pozycja nie ma żadnego aktualnego wypożyczenia i rezerwacji");
            }
            reserveButton.setDisable(false);
        }
    }




    public void setFoundBookData(Book foundBookDateTab[]) {

        List<Book> foundBookList = Arrays.asList(foundBookDateTab);
        foundBookData = FXCollections.observableArrayList(foundBookList);

    }



    public boolean setSearchResultTable() throws URISyntaxException {

        String phraseType = selectedBook.getPhraseType();
        switch(phraseType) {
            case "Tytuł":
                if (libraryData.setBookDataByTitlePart(selectedBook.getPhrasePart()) == true) {
                    setTableColumns();
                    return true;
                }
                else
                    return false;
            case "ISBN":
                if(libraryData.setBookDataIsbnPart(selectedBook.getPhrasePart()) == true) {
                    setTableColumns();
                    return true;
                }
                else
                    return false;
            case "Autor":
                if(libraryData.setBookDataAuthorPart(selectedBook.getPhrasePart()) == true) {
                    setTableColumns();
                    return true;
                }
                else
                    return false;
        }
        return false;
    }

    public void setTableColumns () {

        searchResultTableTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        searchResultTableISBNColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        searchResultTablePublishingHouseColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publishingHouse"));
        searchResultTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("AuthorsNames"));
        searchResultTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("CategoriesName"));
        searchResultTableAliasColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("AliasesNames"));
        searchResultTableView.setItems(foundBookData);
    }

    @Override
    public boolean setWindow() throws URISyntaxException{

        reserveButton.setDisable(true);
        if (setSearchResultTable() == false)
            return false;
        return true;
    }
}
