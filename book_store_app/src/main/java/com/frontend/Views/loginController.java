package com.frontend.Views;

import com.DBO.SignIn;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class loginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passField;

    @FXML
    private Label unValidInputLabel;

    @FXML
    private Label signInLabel;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button okButton;

    @FXML
    void onLoginButton(Event event) throws IOException {
        loginButton.requestFocus();
        String username = usernameTextField.getText();
        String pass = passField.getText();
        if(pass.equals("")){
            unValidInputLabel.setVisible(true);
            passField.requestFocus();
            passField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
        }
        else{
            passField.setStyle("-fx-border-color: #462602; -fx-border-width: 0px");
        }
        if(username.equals("")){
            unValidInputLabel.setVisible(true);
            usernameTextField.requestFocus();
            usernameTextField.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 15; -fx-background-color: #AEDAF8");
        }
        else{
            usernameTextField.setStyle("-fx-border-color: #462602; -fx-border-width: 0px");
        }
        if(!username.equals("") && !pass.equals("")){
            // send to database to check
            SignIn signIn = new SignIn();
            if(signIn.run(username,pass)) {
                closeLoginView();
                new profileController().profileView();
            }
            else {
                usernameTextField.requestFocus();
                unValidInputLabel.setVisible(true);
            }
        }
    }

    @FXML
    void onSignUpButton(MouseEvent event){
        closeLoginView();
        signUpController signUpController = new signUpController();
        try {
            signUpController.signUpView();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void onUsernameFieldEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) passField.requestFocus();
        else {
            usernameTextField.requestFocus();
        }
    }

    @FXML
    void onPassFieldEnter(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            loginButton.requestFocus();
            onLoginButton(event);
        }
        else {
            passField.requestFocus();
        }
    }

    @FXML
    void onUsernameFieldClick(){
        usernameTextField.requestFocus();
    }

    @FXML
    public void onPassFieldClick(MouseEvent mouseEvent) {
        passField.requestFocus();
    }

    public void loginView() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        loginStage.setTitle("Book Store");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    public void closeLoginView(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onOkButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
        loginView();
    }

}
