package com.frontend.Views;

import com.DBO.addBook;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class addBookController {

    @FXML
    private Label unValidInputLabel;

    @FXML
    private Label CategoryLabel;

    @FXML
    private Label PublicationYearLabel;

    @FXML
    private Label PublisherNameLabel;

    @FXML
    private Label SellingPriceLabel;

    @FXML
    private Label authorNameLabel;

    @FXML
    private TextField authorNameTextField;

    @FXML
    private Button backButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private Label numOfCopiesLabel;

    @FXML
    private TextField numOfCopiesTextField;

    @FXML
    private TextField publicationYearTextField;

    @FXML
    private TextField publisherNameTextField;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    private Label thresholdLabel;

    @FXML
    private TextField thresholdTextField;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private Label validationMessage;

    @FXML
    private Button uploadButton;


    @FXML
    void onUpload(Event event) {
        String title = titleTextField.getText();
        String authorName = authorNameTextField.getText();
        String publisherName = publisherNameTextField.getText();
        String publicationYear = publicationYearTextField.getText();
        String sellingPrice = sellingPriceTextField.getText();
        String category = categoryTextField.getText();
        String numOfCopies = numOfCopiesTextField.getText();
        String threshold = thresholdTextField.getText();

        if(title.equals("")){
            titleTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            titleTextField.requestFocus();
        }
        if(authorName.equals("")){
            authorNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            authorNameTextField.requestFocus();
        }
        if(publisherName.equals("")){
            publisherNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            publisherNameTextField.requestFocus();
        }
        if(publicationYear.equals("")){
            publicationYearTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            publicationYearTextField.requestFocus();
        }
        if(sellingPrice.equals("")){
            sellingPriceTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            sellingPriceTextField.requestFocus();
        }
        if(category.equals("")){
            categoryTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            categoryTextField.requestFocus();
        }
        if(numOfCopies.equals("")){
            numOfCopiesTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            numOfCopiesTextField.requestFocus();
        }
        if(threshold.equals("")){
            thresholdTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            thresholdTextField.requestFocus();
        }
        if(!title.equals("") && !authorName.equals("") && !publisherName.equals("") && !publicationYear.equals("") && !sellingPrice.equals("") && !numOfCopies.equals("") && !category.equals("") && !threshold.equals("")){
            addBook addBook = new addBook();
            if(addBook.add(title,authorName,publisherName,publicationYear,sellingPrice,category,numOfCopies,threshold)){
                titleTextField.requestFocus();
                titleTextField.setText("");
                titleTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                authorNameTextField.setText("");
                authorNameTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                publisherNameTextField.setText("");
                publisherNameTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                publicationYearTextField.setText("");
                publicationYearTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                sellingPriceTextField.setText("");
                sellingPriceTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                numOfCopiesTextField.setText("");
                numOfCopiesTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                categoryTextField.setText("");
                categoryTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                thresholdTextField.setText("");
                thresholdTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                validationMessage.setVisible(true);
                unValidInputLabel.setVisible(false);
            }
            else{
                validationMessage.setVisible(false);
                unValidInputLabel.setVisible(true);
            }
        }
        else {
            validationMessage.setVisible(false);
            unValidInputLabel.setVisible(true);
        }
    }

    void addBookView() throws IOException {
        Stage addBookStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addBook.fxml")));
        addBookStage.setTitle("Book Store");
        addBookStage.setScene(new Scene(root));
        addBookStage.show();
    }

    public void closeAddBookView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void PublicationYearOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) sellingPriceTextField.requestFocus();
    }

    @FXML
    void authorNameOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) publisherNameTextField.requestFocus();
    }

    @FXML
    void categoryOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) numOfCopiesTextField.requestFocus();
    }

    @FXML
    void numOfCopiesOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) thresholdTextField.requestFocus();
    }

    @FXML
    void publisherNameOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) publicationYearTextField.requestFocus();
    }

    @FXML
    void sellingPriceOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) categoryTextField.requestFocus();
    }

    @FXML
    void thresholdOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            uploadButton.requestFocus();
            onUpload(event);
        }
    }

    @FXML
    void titleFieldOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) authorNameTextField.requestFocus();
    }

    @FXML
    void onBack(MouseEvent mouseEvent) {
        closeAddBookView();
        profileController profileController = new profileController();
        try {
            profileController.profileView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
