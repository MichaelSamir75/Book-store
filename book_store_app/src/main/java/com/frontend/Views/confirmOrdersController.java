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
public class confirmOrdersController {
    @FXML
    private Button backButton;

    @FXML
    private Label book1;

    @FXML
    private Label book2;

    @FXML
    private Label book3;

    @FXML
    private Label book4;

    @FXML
    private Button confirm1;

    @FXML
    private Button confirm2;

    @FXML
    private Button confirm3;

    @FXML
    private Button confirm4;

    @FXML
    private Label errorLabel;

    @FXML
    private Label manager1;

    @FXML
    private Label manager2;

    @FXML
    private Label manager3;

    @FXML
    private Label manager4;

    @FXML
    private Button next;

    @FXML
    private Label orderno1;

    @FXML
    private Label orderno2;

    @FXML
    private Label orderno3;

    @FXML
    private Label orderno4;

    @FXML
    private AnchorPane panel1;

    @FXML
    private AnchorPane panel2;

    @FXML
    private AnchorPane panel3;

    @FXML
    private AnchorPane panel4;

    @FXML
    private Button previous;

    @FXML
    private Label quantity1;

    @FXML
    private Label quantity2;

    @FXML
    private Label quantity3;

    @FXML
    private Label quantity4;
    private ArrayList<String[]> data;
    private ArrayList<String[]> current = new ArrayList<>();
    private ArrayList<String[]> prev = new ArrayList<>();


    @FXML
    void onBack(MouseEvent event) {
        closeOrdersView();
        profileController profileController = new profileController();
        try {
            profileController.profileView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void closeOrdersView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onNext(MouseEvent event) {
        if(data.size() > 0){
            display4();
        }
    }

    @FXML
    void onPrevious(MouseEvent event) {
        if(prev.size() > 0){
            int k = current.size();
            for(int i=0; i<k; i++){
                data.add(0, current.remove(k-1-i));
            }
            k = Math.min(4,prev.size());
            for(int i=0; i<k; i++){
                data.add(0, prev.remove(0));
            }
            display4();
        }
    }
    @FXML
    void onConfirm1(MouseEvent event) {
        Order o = new Order();
        o.ConfirmOrders(current.get(0)[0]);
        current.remove(0);
        confirm1.setVisible(false);
    }

    @FXML
    void onConfirm2(MouseEvent event) {
        Order o = new Order();
        o.ConfirmOrders(current.get(1)[0]);
        current.remove(1);
        confirm2.setVisible(false);
    }

    @FXML
    void onConfirm3(MouseEvent event) {
        Order o = new Order();
        o.ConfirmOrders(current.get(2)[0]);
        current.remove(2);
        confirm3.setVisible(false);
    }

    @FXML
    void onConfirm4(MouseEvent event) {
        Order o = new Order();
        o.ConfirmOrders(current.get(3)[0]);
        current.remove(0);
        confirm4.setVisible(false);
    }

    public void display4(){
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);

        orderno1.setVisible(false);
        orderno2.setVisible(false);
        orderno3.setVisible(false);
        orderno4.setVisible(false);

        int k = current.size();
        for(int i=0; i<k; i++){
            prev.add(0,current.remove(0));
        }
        if(data.size() + current.size() >= 1){
            orderno1.setVisible(true);
            current.add(data.remove(0));
            orderno1.setText("Order #" + Integer.toString((prev.size()+current.size())));
            panel1.setVisible(true);
            confirm1.setVisible(true);
            manager1.setText(current.get(current.size()-1)[1]);
            book1.setText(current.get(current.size()-1)[2]);
            quantity1.setText(current.get(current.size()-1)[3]);
        }
        if(data.size() + current.size() >= 2){
            orderno2.setVisible(true);
            current.add(data.remove(0));
            orderno2.setText("Order #" + Integer.toString((prev.size()+current.size())));
            panel2.setVisible(true);
            confirm2.setVisible(true);
            manager2.setText(current.get(current.size()-1)[1]);
            book2.setText(current.get(current.size()-1)[2]);
            quantity2.setText(current.get(current.size()-1)[3]);
        }
        if(data.size() + current.size() >= 3){
            orderno3.setVisible(true);
            current.add(data.remove(0));
            orderno3.setText("Order #" + Integer.toString((prev.size()+current.size())));
            panel3.setVisible(true);
            confirm3.setVisible(true);
            manager3.setText(current.get(current.size()-1)[1]);
            book3.setText(current.get(current.size()-1)[2]);
            quantity3.setText(current.get(current.size()-1)[3]);
        }
        if(data.size() + current.size() >= 4){
            orderno4.setVisible(true);
            current.add(data.remove(0));
            orderno4.setText("Order #" + Integer.toString((prev.size()+current.size())));
            panel4.setVisible(true);
            confirm4.setVisible(true);
            manager4.setText(current.get(current.size()-1)[1]);
            book4.setText(current.get(current.size()-1)[2]);
            quantity4.setText(current.get(current.size()-1)[3]);
        }
    }
    public void confirmOrdersView() throws IOException {
        Stage ordersStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmOrder.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        ordersStage.setTitle("Book Store");
        ordersStage.setScene(new Scene(root));
        ordersStage.show();

        //display
        Order o = new Order();
        data = o.ShowOrders();
        previous.setVisible(true);
        next.setVisible(true);
        backButton.setVisible(true);
        this.display4();
    }

}
