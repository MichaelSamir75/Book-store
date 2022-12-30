package com.frontend.book_store_front;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class loginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passField;

    @FXML
    private Label signInLabel;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label welcomeLabel;

    @FXML
    void login(MouseEvent event) {
        String username = usernameTextField.getText();
        String pass = passField.getText();
        System.out.println(username + " " + pass);
    }

    @FXML
    void sign_up(MouseEvent event) {
        // move to sign up page
    }

}
