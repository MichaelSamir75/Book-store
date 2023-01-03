package com.frontend.Views;

import com.DBO.ModifyBook;
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

public class editBookInfoController {

    @FXML
    private Label categoryLabel;

    @FXML
    private Label publicationYearLabel;

    @FXML
    private Label publisherNameLabel;

    @FXML
    private Label sellingPriceLabel;

    @FXML
    private Label authorNameLabel;

    @FXML
    private TextField authorNameTextField;

    @FXML
    private Button backButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label numOfCopiesLabel;

    @FXML
    private TextField numOfCopiesTextField;

    @FXML
    private TextField publicationYearTextField;

    @FXML
    private TextField publisherNameTextField;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

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
    private Label inValidInputLabel;

    @FXML
    private Label validationMessage;

    private String titleName;



    @FXML
    void onBack(MouseEvent event) {
        closeEditBookInfoView();
        profileController profileController = new profileController();
        try {
            profileController.profileView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onConfirm(Event event) {
        String title = titleTextField.getText();
        String authorName = authorNameTextField.getText();
        String publisherName = publisherNameTextField.getText();
        String publicationYear = publicationYearTextField.getText();
        String sellingPrice = sellingPriceTextField.getText();
        String category = categoryTextField.getText();
        String numOfCopies = numOfCopiesTextField.getText();
        String threshold = thresholdTextField.getText();

        if(threshold.equals("")){
            thresholdTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            thresholdTextField.requestFocus();
        }
        if(numOfCopies.equals("")){
            numOfCopiesTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            numOfCopiesTextField.requestFocus();
        }
        if(category.equals("")){
            categoryTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            categoryTextField.requestFocus();
        }
        if(sellingPrice.equals("")){
            sellingPriceTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            sellingPriceTextField.requestFocus();
        }
        if(publicationYear.equals("")){
            publicationYearTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            publicationYearTextField.requestFocus();
        }
        if(publisherName.equals("")){
            publisherNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            publisherNameTextField.requestFocus();
        }
        if(authorName.equals("")){
            authorNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            authorNameTextField.requestFocus();
        }
        if(title.equals("")){
            titleTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            titleTextField.requestFocus();
        }
        if(!title.equals("") && !authorName.equals("") && !publisherName.equals("") && !publicationYear.equals("") && !sellingPrice.equals("") && !numOfCopies.equals("") && !category.equals("") && !threshold.equals("")){
            ModifyBook modifyBook = new ModifyBook();
            if(modifyBook.modifyBook(titleName,title,authorName,publisherName,publicationYear,sellingPrice,category,numOfCopies,threshold)){
                searchTextField.requestFocus();
                titleTextField.setVisible(false);
                authorNameTextField.setVisible(false);
                publisherNameTextField.setVisible(false);
                publicationYearTextField.setVisible(false);
                sellingPriceTextField.setVisible(false);
                numOfCopiesTextField.setVisible(false);
                categoryTextField.setVisible(false);
                thresholdTextField.setVisible(false);
                validationMessage.setVisible(true);
                searchTextField.setText("");
                titleLabel.setVisible(false);
                authorNameLabel.setVisible(false);
                publisherNameLabel.setVisible(false);
                publicationYearLabel.setVisible(false);
                sellingPriceLabel.setVisible(false);
                categoryLabel.setVisible(false);
                numOfCopiesLabel.setVisible(false);
                thresholdLabel.setVisible(false);
                inValidInputLabel.setVisible(false);
                confirmButton.setVisible(false);
            }
            else{
                validationMessage.setVisible(false);
                inValidInputLabel.setVisible(true);
            }
        }
        else {
            validationMessage.setVisible(false);
            inValidInputLabel.setVisible(true);
        }
    }

    @FXML
    void onSearch(Event event) {
        searchButton.requestFocus();
        validationMessage.setVisible(false);
        String book = searchTextField.getText();
        if(book.equals("")){
            inValidInputLabel.setVisible(true);
            searchTextField.requestFocus();
        }
        else{
            ModifyBook modifyBook = new ModifyBook();
            String[] res = modifyBook.getContent(book);
            if(res == null) {
                inValidInputLabel.setVisible(true);
                searchTextField.requestFocus();
                titleLabel.setVisible(false);
                titleTextField.setVisible(false);
                authorNameLabel.setVisible(false);
                authorNameTextField.setVisible(false);
                publisherNameLabel.setVisible(false);
                publisherNameTextField.setVisible(false);
                publicationYearLabel.setVisible(false);
                publicationYearTextField.setVisible(false);
                sellingPriceLabel.setVisible(false);
                sellingPriceTextField.setVisible(false);
                categoryLabel.setVisible(false);
                categoryTextField.setVisible(false);
                numOfCopiesLabel.setVisible(false);
                numOfCopiesTextField.setVisible(false);
                thresholdLabel.setVisible(false);
                thresholdTextField.setVisible(false);
                confirmButton.setVisible(false);
            }
            else{
                titleName = res[0];
                inValidInputLabel.setVisible(false);
                titleLabel.setVisible(true);
                titleTextField.setVisible(true);
                titleTextField.setText(res[0]);
                authorNameLabel.setVisible(true);
                authorNameTextField.setVisible(true);
                authorNameTextField.setText(res[1]);
                publisherNameLabel.setVisible(true);
                publisherNameTextField.setVisible(true);
                publisherNameTextField.setText(res[2]);
                publicationYearLabel.setVisible(true);
                publicationYearTextField.setVisible(true);
                publicationYearTextField.setText(res[3]);
                sellingPriceLabel.setVisible(true);
                sellingPriceTextField.setVisible(true);
                sellingPriceTextField.setText(res[4]);
                categoryLabel.setVisible(true);
                categoryTextField.setVisible(true);
                categoryTextField.setText(res[5]);
                numOfCopiesLabel.setVisible(true);
                numOfCopiesTextField.setVisible(true);
                numOfCopiesTextField.setText(res[6]);
                thresholdLabel.setVisible(true);
                thresholdTextField.setVisible(true);
                thresholdTextField.setText(res[7]);
                confirmButton.setVisible(true);
            }
        }
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
            confirmButton.requestFocus();
            onConfirm(event);
        }
    }

    @FXML
    void titleFieldOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) authorNameTextField.requestFocus();
    }

    void editBookInfoView() throws IOException {
        Stage addBookStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editBookInfo.fxml")));
        addBookStage.setTitle("Book Store");
        addBookStage.setScene(new Scene(root));
        addBookStage.show();
    }

    public void closeEditBookInfoView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchFieldOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchButton.requestFocus();
            onSearch(event);
        }
    }

}
