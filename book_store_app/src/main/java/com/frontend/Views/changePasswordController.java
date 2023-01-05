package com.frontend.Views;

import com.DBO.SignIn;
import com.DBO.editInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class changePasswordController {
    @FXML
    private PasswordField oldPass ;
    @FXML
    private PasswordField pass ;
    @FXML
    private PasswordField reenterPass ;
    @FXML
    private Button confirmButton ;

    @FXML
    private Label ErrorLabel ;
    String[] getData(){
        String[] result = new String[2] ;
        if(oldPass.getText().equals("")){
            oldPass.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null ;
        }else if(result != null){
            result[0] = oldPass.getText();
        }
        if(pass.getText().equals("")){
            pass.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null ;
        }else if(result != null){
            result[1] = pass.getText();
        }
        if(reenterPass.getText().equals("") || reenterPass.getText().compareTo(pass.getText()) != 0){
            System.out.println(reenterPass.getText());
            System.out.println(pass.getText());
            reenterPass.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }
        return result ;
    }

    void returnToHomePage(MouseEvent event) throws IOException {
        closeEditView() ;
        new profileController().profileView() ;
    }
    void closeEditView(){
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmButton(MouseEvent event) throws IOException {
        editInfo e = new editInfo() ;
        String pass = e.getOldPassword(SignIn.userID) ;
        String[] data = getData() ;
        Boolean res = false ;
        if(Objects.equals(data[0], pass)){
            res = e.saveNewPassword(SignIn.userID ,data[1]) ;
        }
        if(res){
            returnToHomePage(event) ;
        }else{
            ErrorLabel.setVisible(true);
            ErrorLabel.setText("Make Sure that you Entered the Right Old Password") ;
        }
    }

    @FXML
    void onBackButton(MouseEvent event) throws IOException, SQLException {
        closeEditView() ;
        new editInfoController().editInfoView() ;
    }

    @FXML
    void editPasswordView() throws IOException{
        Stage stage = new Stage() ;
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("changePassword.fxml")))) ;
        stage.setTitle("Book Store") ;
        stage.setScene(new Scene(root)) ;
        stage.show() ;
    }

}
