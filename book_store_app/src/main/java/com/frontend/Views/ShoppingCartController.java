package com.frontend.Views;

import com.DBO.Checkout;
import com.DBO.Order;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCartController {
    public static ArrayList<String[]> items = new ArrayList<>();
    @FXML
    private Button previous;
    @FXML
    private Label quantity;
    @FXML
    private Label publisher;
    @FXML
    private Label author;
    @FXML
    private Label category;
    @FXML
    private Label year;
    @FXML
    private Label isbn;
    @FXML
    private Label price;
    @FXML
    private Label book;
    @FXML
    public Label outOfStock;
    @FXML
    private Button next;
    @FXML
    private Button delete;
    @FXML
    private Button checkout;
    @FXML
    private Button backButton;
    @FXML
    private Label item;

    @FXML
    private AnchorPane panel1;
    @FXML
    private AnchorPane panel2;

    @FXML
    private Label totalPrice;

    @FXML
    private TextField quantityText;

    int index = 0;


    void cartView() throws IOException {
        Stage cartStage = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("ShoppingCart.fxml")
        );
        loader.setController(this);
        Parent root = (Parent) loader.load();
        outOfStock.setVisible(false);
        cartStage.setTitle("Shopping Cart");
        cartStage.setScene(new Scene(root));
        cartStage.show();
        onShow(null);
    }
    @FXML
    void onBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        profileController profileController = new profileController();
        profileController.profileView();
    }
    @FXML
    void onCheckout(MouseEvent event) throws IOException, SQLException {
        Stage stage = (Stage) checkout.getScene().getWindow();
        stage.close();
        CheckoutController checkoutController = new CheckoutController();
        checkoutController.checkout = new Checkout();
        checkoutController.checkoutView();
    }

    @FXML
    void onDelete(MouseEvent event) {
        items.remove(index);
        onPrevious(event);
    }

    @FXML
    void onNext(MouseEvent event) {
        index++;
        if(index > items.size()-1)
            index = items.size() - 1;
        if(items.size() > 0){
            showCart();
        }
    }

    @FXML
    void onPrevious(MouseEvent event) {
        index--;
        if(index == -1)
            index = 0;
        showCart();
    }
    @FXML
    void quantityOnEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            quantityText.requestFocus();
            try{
                int q = Integer.parseInt(quantityText.getText());
                if(q < 1){
                    quantityText.setText("1");
                }
            }
            catch (NumberFormatException nfe){
                quantityText.setText("1");
            }
            items.get(index)[7] = quantityText.getText();
            showCart();
        }
    }

    public void showCart(){
        panel1.setVisible(false);
        updateTotalPrice();
        panel2.setVisible(true);
        item.setVisible(false);
        outOfStock.setVisible(false);

        if(items.size() > 0){
            item.setVisible(true);
            panel1.setVisible(true);
            item.setText("Item #" + (index+1));
            panel1.setVisible(true);
            isbn.setText("ISBN: " + items.get(index)[0]);
            book.setText("Book Title: " + items.get(index)[1]);
            author.setText("Author Name: " + items.get(index)[2]);
            category.setText("Category: " + items.get(index)[3]);
            publisher.setText("Publisher Name: " + items.get(index)[4]);
            year.setText("Publication Year: " + items.get(index)[5]);
            price.setText("Price: " + items.get(index)[6]);
          //  quantity.setText("Quantity: " + items.get(index)[7]);
            quantityText.setText(items.get(index)[7]);
        }
        else {
            checkout.setVisible(false);
        }
    }
    void updateTotalPrice() {
        double price = 0;
        for(int i=0; i<items.size(); i++) {
            price += (Double.parseDouble(items.get(i)[6]) * Double.parseDouble(items.get(i)[7]));
        }
        totalPrice.setText("Total Price: " + price);
    }


    void onShow(MouseEvent event) {
        // title author publisher year category price quantity
        String[] boook = {"5", "publisher yara", "author yara","horror", "mariam", "2022", "250", "10"};
        items.add(boook);
        String[] bookk = {"2", "publisher yara", "author yara","horror", "mariam", "2022", "250", "30"};
        items.add(bookk);
        String[] boaokk = {"7", "mariam yara", "author yara","horror", "mariam", "2022", "250", "10"};
        items.add(boaokk);
        previous.setVisible(true);
        next.setVisible(true);
        backButton.setVisible(true);
        showCart();
    }

}
