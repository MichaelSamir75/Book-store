package com.frontend.Views;

import com.DBO.Order;
import com.DBO.addBook;
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
import javafx.event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class placeOrderController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Label quantityLabel;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private Label unValidInputLabel;

    @FXML
    private Label validationMessage;

    @FXML
    void titleFieldOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) quantityTextField.requestFocus();
    }


    @FXML
    void quantityFieldOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            confirmButton.requestFocus();
            onConfirm(event);
        }
    }

    @FXML
    void onBack(MouseEvent event) {
        closePlaceOrderView();
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
        String quantity = quantityTextField.getText();

        if(quantity.equals("")){
            quantityTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            quantityTextField.requestFocus();
        }
        if(title.equals("")){
            titleTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
            titleTextField.requestFocus();
        }

        if(!title.equals("") && !quantity.equals("")){
            Order order = new Order();
            if(order.placeOrder(title,quantity)){
                titleTextField.requestFocus();
                titleTextField.setText("");
                titleTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
                quantityTextField.setText("");
                quantityTextField.setStyle("-fx-background-radius: 15; -fx-background-color: #AEDAF8");
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

    void placeOrderView() throws IOException {
        Stage addBookStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("placeOrder.fxml")));
        addBookStage.setTitle("Book Store");
        addBookStage.setScene(new Scene(root));
        addBookStage.show();
    }

    public void closePlaceOrderView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
