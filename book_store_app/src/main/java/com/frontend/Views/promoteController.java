package com.frontend.Views;
import com.DBO.promotion;
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
public class promoteController {
    @FXML
    private Button backButton;

    @FXML
    private TextField email;
    @FXML
    private Label errorLabel;

    @FXML
    private Button promoteButton;

    @FXML
    void onBack(MouseEvent event) {
        closePromoteView();
        profileController profileController = new profileController();
        try {
            profileController.profileView();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void closePromoteView(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void onPromote(MouseEvent event) {
        promotion p = new promotion();
        String Email = email.getText();
        if(Email.equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("Enter an E-mail");
            errorLabel.setStyle("-fx-text-fill: red");
        }else{
            if(p.promote(email.getText())){
                errorLabel.setVisible(true);
                errorLabel.setText("User promoted successfully");
                errorLabel.setStyle("-fx-text-fill: #34ec3a");
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("User not found");
                errorLabel.setStyle("-fx-text-fill: red");
            }
        }
    }
    public void promoteView() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("promote.fxml")));
        loginStage.setTitle("Book Store");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }
}
