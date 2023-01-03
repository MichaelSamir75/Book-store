package com.frontend.Views;

import com.DBO.Book;
import com.DBO.Library;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public VBox list;
    public Button backBtn;
    public ScrollPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        try {
            HashMap<Integer, Book> allBooks = Library.getAllBooks();
            showBooks(allBooks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showBooks(HashMap<Integer, Book> books) {
        list.getChildren().clear();
        for(Map.Entry<Integer, Book> entry : books.entrySet()) {
            VBox card = createBookCard(entry.getValue());
            list.getChildren().add(card);
        }
    }

    public VBox createBookCard(Book book) {
        HBox title = createLabelGroup("Title:  ", book.getTitle());
        HBox publisher = createLabelGroup("Publisher:  ", book.getPublisher());
        HBox publicationYear = createLabelGroup("Publication Year:  ", book.getPublicationYear());
        HBox price = createLabelGroup("Price:  ", book.getPrice() + "$");
        HBox category = createLabelGroup("Category:  ", book.getCategory());

        Button addToCartBtn = new Button();
        addToCartBtn.setText("Add To Cart");
        addToCartBtn.setStyle("-fx-background-color: #e81034; -fx-background-radius : 10;");

        HBox box = new HBox(addToCartBtn);
        box.setAlignment(Pos.BASELINE_CENTER);

        VBox card = new VBox(title, publisher, publicationYear, price, category, box);
        card.setStyle("-fx-border-radius: 50px; -fx-border-width: 5px; -fx-border-color: #ffaa4f;");
        card.setPadding(new Insets(20));

        return card;
    }

    HBox createLabelGroup(String key, String value) {
        Label keyLabel = new Label(key);
        Label valueLabel = new Label(value);

        keyLabel.setStyle("-fx-font-size: 20; -fx-text-background-color: yellow");
        valueLabel.setStyle("-fx-font-size: 20; -fx-text-background-color: white");

        return new HBox(keyLabel, valueLabel);
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
    
    public void onBack(MouseEvent mouseEvent) throws IOException {
        closeSearchView();
        profileController controller = new profileController();
        controller.profileView();
    }
    
    public void closeSearchView() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }

}
