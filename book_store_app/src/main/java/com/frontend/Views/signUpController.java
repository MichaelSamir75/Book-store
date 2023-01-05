package com.frontend.Views;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class signUpController {
    @FXML
    private Label ErrorLabel;
    @FXML
    private TextField LastName;

    @FXML
    private TextField UserName;

    @FXML
    private TextField address;

    @FXML
    private Button createButton;

    @FXML
    private TextField email;

    @FXML
    private Button loginButton;

    @FXML
    private TextField pass;

    @FXML
    private TextField phone;

    @FXML
    private TextField reenterPass;

    @FXML
    private Label signInLabel;

    @FXML
    private TextField firstName;

    @FXML
    private Label welcomeLabel;
    @FXML
    void onLoginButton(MouseEvent event) throws IOException {
        closeSignUpView() ;
        loginController loginController = new loginController() ;
        loginController.loginView() ;
    }

    @FXML
    void CreateAccount(MouseEvent event) throws IOException {
        com.DBO.SignUp su = new com.DBO.SignUp();
        String[] data = checkDataFeild();
        Boolean res = false;
        if(data != null) res = su.run(data);
        if(res){
            onLoginButton(event);
        }else{
            ErrorLabel.setVisible(true);
            ErrorLabel.setText("Email already have an account...try to sign in");
        }
    }
    String[] checkDataFeild(){
        String[] result = new String[7];
        if(firstName.getText().equals("")) {
            firstName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[3] = firstName.getText();
        }
        if(LastName.getText().equals("")){
            LastName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[4] = LastName.getText();
        }
        if(phone.getText().equals("")){
            phone.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[2] = phone.getText();
        }
        if(email.getText().equals("")){
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[5] = email.getText();
        }
        if(pass.getText().equals("")){
            pass.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[1] = pass.getText();
        }
        if(reenterPass.getText().equals("") || reenterPass.getText().compareTo(pass.getText()) != 0){
            System.out.println(reenterPass.getText());
            System.out.println(pass.getText());
            reenterPass.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }
        if(address.getText().equals("")){
            address.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[6] = address.getText();
        }
        if(UserName.getText().equals("")){
            UserName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[0] = UserName.getText();
        }
        return result;
    }

    void signUpView() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        loginStage.setTitle("Book Store");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    void closeSignUpView(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
