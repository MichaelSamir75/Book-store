package com.frontend.Views;

import com.DBO.Book;
import com.DBO.Library;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class LibraryController implements Initializable {
    public TextField searchBar;
    public ComboBox<String> searchBy;
    public Button searchBtn;
    public GridPane grid;
    public Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            HashMap<Integer, Book> allBooks = Library.getAllBooks();
            showBooks(allBooks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showBooks(HashMap<Integer, Book> books) {
        grid.getChildren().clear();
        int row = 0, col = 0;
        for(Map.Entry<Integer, Book> entry : books.entrySet()) {
            if(col == 3) {
                row++;
                col = 0;
            }
            VBox card = createBookCard(entry.getValue());
            grid.add(card, col, row);
            col++;
        }
    }

    public VBox createBookCard(Book book) {
        Label titleLabel = new Label("Title: " + book.getTitle());
        Label publisherLabel = new Label("Publisher: " + book.getPublisher());
        Label publicationYearLabel = new Label("Publication Year: " + book.getPublicationYear());
        Label priceLabel = new Label("Price: " + book.getPrice() + "$");
        Label categoryLabel = new Label("Category: " + book.getCategory());

        setStyle(titleLabel, publisherLabel, publicationYearLabel, priceLabel, categoryLabel);
        return new VBox(titleLabel, publisherLabel, publicationYearLabel, priceLabel, categoryLabel);
    }

    void setStyle(Node... nodes) {
        for(Node node : nodes) {
            node.setStyle("-fx-font-size: 20; -fx-text-background-color: white");
        }
    }

    void libraryView() throws IOException {
        Stage libraryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("library.fxml")));
        libraryStage.setTitle("Book Store");
        libraryStage.setScene(new Scene(root));
        libraryStage.show();
    }

    public void onSearch(MouseEvent mouseEvent) throws SQLException {
        String searchTerm = searchBar.getText();
        String searchAttr = searchBy.getValue();
        if(searchAttr == null) {
            System.out.println("ERROR");
            return;
        }
        else if(searchAttr.equals("Title")) searchAttr = "title";
        else if(searchAttr.equals("Publisher")) searchAttr = "publisherName";
        else if(searchAttr.equals("Publication Year")) searchAttr = "publicationYear";
        else if(searchAttr.equals("Category")) searchAttr = "category";
        HashMap<Integer, Book> matchingBooks = Library.getMatchingBooks(searchTerm, searchAttr);
        showBooks(matchingBooks);
    }
    
    public void onBack(MouseEvent mouseEvent) {
        
    }
    
    public void closeSearchView() {
        
    }

}
