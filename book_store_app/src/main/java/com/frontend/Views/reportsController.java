package com.frontend.Views;

import com.DBO.Order;
import com.DBO.ShowReport;
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
public class reportsController {

    @FXML
    private Button backButton;

    @FXML
    private Button books;

    @FXML
    private Button customers;

    @FXML
    private Button sales;

    @FXML
    void onBack(MouseEvent event) {
        closeReportsView();
        profileController profileController = new profileController();
        try {
            profileController.profileView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void closeReportsView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onBooks(MouseEvent event) {
        new ShowReport().runTop10();
    }

    @FXML
    void onCustomers(MouseEvent event) {
        new ShowReport().runTop5();

    }

    @FXML
    void onSales(MouseEvent event) {
        new ShowReport().runTotalSales();

    }

    void reportsView() throws IOException {
        Stage reportStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showReports.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        reportStage.setTitle("Book Store");
        reportStage.setScene(new Scene(root));
        reportStage.show();
    }
}
