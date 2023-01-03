package com.frontend.Views;

import com.DBO.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button show;
    @FXML
    private AnchorPane panel1;
    @FXML
    private AnchorPane panel2;

    @FXML
    private Label totalPrice;

    int index = 0;

    void cartView() throws IOException {
        Stage cartStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShoppingCart.fxml")));
        cartStage.setTitle("Shopping Cart");
        cartStage.setScene(new Scene(root));
        cartStage.show();
    }
    @FXML
    void onBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        profileController profileController = new profileController();
        profileController.profileView();
    }
    @FXML
    void onCheckout(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        CheckoutController.checkoutView();
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
    public void showCart(){
        panel1.setVisible(false);
        panel2.setVisible(true);
        item.setVisible(false);

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
            quantity.setText("Quantity: " + items.get(index)[7]);
        }
    }
    void updateTotalPrice() {

    }
    @FXML
    void onShow(MouseEvent event) {
        // title author publisher year category price quantity
        String[] boook = {"1", "publisher yara", "author yara","horror", "mariam", "2022", "26666622", "2000"};
        items.add(boook);
        String[] bookk = {"2", "publisher yara", "author yara","horror", "mariam", "2022", "26666622", "2000"};
        items.add(bookk);
        String[] boaokk = {"3", "mariam yara", "author yara","horror", "mariam", "2022", "26666622", "2000"};
        items.add(boaokk);
        show.setVisible(false);
        previous.setVisible(true);
        next.setVisible(true);
        backButton.setVisible(true);
        showCart();
    }
}
