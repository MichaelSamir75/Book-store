package com.frontend.Views;

import com.DBO.SignIn;
import com.DBO.editInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class editInfoController {
    @FXML
    private TextField LastName ;
    @FXML
    private TextField UserName ;
    @FXML
    private TextField address ;
    @FXML
    private Label ErrorLabel ;
    @FXML
    private TextField phone;
    @FXML
    private TextField firstName;
    @FXML
    private Button confirmButton ;

    @FXML
    private Button changePasswordButton ;


    String[] checkDataField(){
        String[] result = new String[5] ;
        if(firstName.getText().equals("")) {
            firstName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[2] = firstName.getText() ;
        }
        if(LastName.getText().equals("")){
            LastName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[3] = LastName.getText() ;
        }
        if(phone.getText().equals("")){
            phone.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[1] = phone.getText() ;
        }
        if(address.getText().equals("")){
            address.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[4] = address.getText() ;
        }
        if(UserName.getText().equals("")){
            UserName.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            result = null;
        }else if(result != null){
            result[0] = UserName.getText() ;
        }
        return result;
    }

    @FXML
    void onBackButton(MouseEvent event) throws IOException {
        returnToHomePage(event) ;
    }

    @FXML
    void onConfirmButton(MouseEvent event) throws IOException {
        editInfo e = new editInfo() ;
        String[] data = checkDataField() ;
        Boolean res = false ;
        if(data != null) res = e.run(data ,SignIn.userID) ;
        if(res){
            returnToHomePage(event);
        }else{
            ErrorLabel.setVisible(true) ;
            ErrorLabel.setText("Fields Can not Be Empty") ;
        }
    }

    @FXML
    void onChangePasswordButton(MouseEvent event) throws IOException {
        changePass() ;
        new changePasswordController().editPasswordView() ;
    }

    void changePass(){
        Stage stage = (Stage) changePasswordButton.getScene().getWindow() ;
        stage.close() ;
    }
    void returnToHomePage(MouseEvent event) throws IOException {
        closeEditView() ;
        new profileController().profileView() ;
    }
    void closeEditView(){
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    void showOldInfo() throws SQLException {
        editInfo e = new editInfo() ;
        String[] info = e.getUserInfo(SignIn.userID) ;
        UserName.setText(info[0]) ;
        phone.setText(info[1]) ;
        firstName.setText(info[2]) ;
        LastName.setText(info[3]) ;
        address.setText(info[4]) ;
    }

    private String readEmailInfo(){
        String email = "";
        try {
            File myObj = new File("emailInfo");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                email = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return email;
    }
    @FXML
     void editInfoView() throws IOException, SQLException {
        Stage stage = new Stage() ;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editInfo.fxml")) ;
        loader.setController(this) ;
        Parent root = loader.load() ;
        showOldInfo() ;
        stage.setTitle("Book Store") ;
        stage.setScene(new Scene(root)) ;
        stage.show() ;
    }
}
