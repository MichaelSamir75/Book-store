package com.frontend.Views;

import com.DBO.Checkout;
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
import java.sql.Date;
import java.sql.SQLException;
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

    Checkout checkout;
    public CheckoutController() throws SQLException {
        checkout = new Checkout();
    }

    @FXML
    public void onConfirm(MouseEvent mouseEvent) throws SQLException {
        invalid.setVisible(false);
        valid.setVisible(false);
        boolean check = checkout.validateCredentials(cardNoText.getText(),
                Date.valueOf(expiryDateText.getText()));
        if(check) {
            valid.setVisible(true);
            confirm.setVisible(false);
            ShoppingCartController.items.clear();
        }
        else
            invalid.setVisible(true);
    }
    @FXML
    public void onBack(MouseEvent mouseEvent) throws IOException, SQLException {
        checkout.rollBack();
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.cartView();
    }
    public void checkoutView() throws IOException, SQLException {
        if(checkout.availableQuantity()) {
            Stage checkoutStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(CheckoutController.class.getResource("Checkout.fxml")));
            checkoutStage.setTitle("Checkout");
            checkoutStage.setScene(new Scene(root));
            checkoutStage.show();
        }
        else {
            ShoppingCartController shoppingCartController = new ShoppingCartController();
            shoppingCartController.cartView();
        }
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
