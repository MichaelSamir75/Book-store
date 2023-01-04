package com.frontend.Views;

import com.DBO.SignIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
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

    @FXML Button editInfoButton ;

    @FXML
    private Button editProfileButton;

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
            ShoppingCartController.items.clear();
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
    void onSearch(MouseEvent event) throws IOException {
        closeProfileView();
        LibraryController library = new LibraryController();
        library.libraryView();
    }

    @FXML
    void onShopCart(MouseEvent event) throws IOException {
        closeProfileView();
        ShoppingCartController cart = new ShoppingCartController();
        cart.cartView();


    }

    @FXML
    void onTopCustomers(MouseEvent event) {

    }

    @FXML
    void onEditProfile(MouseEvent event) throws SQLException, IOException {
        closeProfileView();
        editInfoController el = new editInfoController();
        el.editInfoView() ;
    }

    void profileView() throws IOException {
        Stage profileStage = new Stage();
        Parent root;
        SignIn signIn = new SignIn();
        // if user
        if(signIn.DefineUser() == 1){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UserHomepage.fxml")));
        }
        // if manager
        else{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("managerHomePage.fxml")));
        }
        profileStage.setTitle("Book Store");
        profileStage.setScene(new Scene(root));
        profileStage.show();
    }

    public void closeProfileView(){
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

}
