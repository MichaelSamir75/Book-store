package com.frontend.Views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class profileController {

    @FXML
    private Button addBookButton;

    @FXML
    private Button confirmOrdersButton;

    @FXML
    private Button editDataButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button promoteUsersButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button shopCartButton;

    @FXML
    private Button topCustomersButton;

    @FXML
    void onAddBook(MouseEvent event) {
        closeProfileView();
        addBookController addBookController = new addBookController();
        try {
            addBookController.addBookView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onConfirmOrders(MouseEvent event) {
        closeProfileView();
        confirmOrdersController c = new confirmOrdersController();
        try {
            c.confirmOrdersView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onEditData(MouseEvent event) {
        closeProfileView();
        editBookInfoController editBookInfoController = new editBookInfoController();
        try {
            editBookInfoController.editBookInfoView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onLogOut(MouseEvent event) {
        closeProfileView();
        loginController loginController = new loginController();
        try {
            loginController.loginView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onPromoteUsers(MouseEvent event) {
        closeProfileView();
        promoteController promoteController = new promoteController();
        try {
            promoteController.promoteView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onPlaceOrder(MouseEvent event) {
        closeProfileView();
        placeOrderController placeOrderController = new placeOrderController();
        try {
            placeOrderController.placeOrderView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onSearch(MouseEvent event) {

    }

    @FXML
    void onShopCart(MouseEvent event) {

    }

    @FXML
    void onTopCustomers(MouseEvent event) {

    }

    void profileView() throws IOException {
        Stage profileStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));
        profileStage.setTitle("Book Store");
        profileStage.setScene(new Scene(root));
        profileStage.show();
    }

    public void closeProfileView(){
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

}
