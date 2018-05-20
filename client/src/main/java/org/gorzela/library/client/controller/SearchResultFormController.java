package org.gorzela.library.client.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gorzela.library.client.Data.LibraryData;
import org.gorzela.library.client.util.BookSearchTrio;
import org.gorzela.library.client.util.ErrorInformation;
import org.gorzela.library.client.view.SelectedItemStateFormView;
import org.gorzela.library.common.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@FXMLController
public class SearchResultFormController extends AbstractFormController {

    @Autowired
    private LibraryData libraryData;

    @Autowired
    private ErrorInformation errorInformation;

    @Autowired
    private BookSearchTrio bookSearchTrio;

    @Autowired
    private SelectedItemStateFormView selectedItemStateWindow;

    @Autowired
    private SelectedItemStateFormController selectedItemStateController;

    private ObservableList<Book> bookData;

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
    private Button closeSearchResultFormButton;

    @FXML
    private Button showSelectedButton;

    @FXML
    public void closeSearchResultFormAction(ActionEvent event) {

        closeWindow((Button)event.getSource());

    }

    @FXML
    public void showSelectedAction(ActionEvent event) {

        if(searchResultTableView.getSelectionModel().getSelectedItem() == null) {

            errorInformation.showInformation("Błąd", "Nie wybrałeś żadnej pozycji do wglądu...");
        }
        else {
            bookSearchTrio.setSelectedBookId(searchResultTableView.getSelectionModel().getSelectedItem().getBookId());
            createAndShowWindow(selectedItemStateWindow, selectedItemStateController, "Wybrana pozycja");
        }
    }

    public void setBookData(Book bookDateTab[]) {

        List<Book> bookList = Arrays.asList(bookDateTab);
        bookData = FXCollections.observableArrayList(bookList);

    }

    public boolean setSearchResultTable() throws URISyntaxException {

        String phraseType = bookSearchTrio.getPhraseType();
        switch(phraseType) {
            case "Tytuł":
                if (libraryData.setBookDataByTitlePart(bookSearchTrio.getPhrasePart()) == true) {
                    setTableColumns();
                    return true;
                }
                else
                    return false;
            case "ISBN":
                if(libraryData.setBookDataIsbnPart(bookSearchTrio.getPhrasePart()) == true) {
                    setTableColumns();
                    return true;
                }
                else
                    return false;
            case "Autor":
                if(libraryData.setBookDataAuthorPart(bookSearchTrio.getPhrasePart()) == true) {
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
        searchResultTableView.setItems(bookData);
    }

    @Override
    public boolean setWindow() throws URISyntaxException{
        if (setSearchResultTable() == false)
            return false;
        return true;
    }
}
