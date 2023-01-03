package com.frontend.Views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class CheckoutController {
    @FXML
    private Label cardNo;
    @FXML
    private TextField cardNoText;
    @FXML
    private Label expiryDate;
    @FXML
    private TextField expiryDateText;

    @FXML
    private Button confirm;
    @FXML
    private Button back;

    @FXML
    private Label valid;
    @FXML
    private Label invalid;

    @FXML
    public void onConfirm(MouseEvent mouseEvent) {
        // call class

    }
    @FXML
    public void onBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.cartView();
    }
    static void checkoutView() throws IOException {
        Stage checkoutStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(CheckoutController.class.getResource("Checkout.fxml")));
        checkoutStage.setTitle("Checkout");
        checkoutStage.setScene(new Scene(root));
        checkoutStage.show();
    }
    @FXML
    void cardNoOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) cardNoText.requestFocus();
    }

    @FXML
    void expiryDateOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) expiryDateText.requestFocus();
    }
}
