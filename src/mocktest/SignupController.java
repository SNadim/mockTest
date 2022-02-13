/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocktest;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mocktest.config.dbconnect;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignupController implements Initializable {

     @FXML
    private TextField txt_email_up;

    @FXML
    private TextField txt_password_up;

    @FXML
    private Button btn_login_up;

    @FXML
    private Button btn_signup_up;

    @FXML
    private ComboBox<?> type_up;

    @FXML
    private TextField txt_username_up;
    
    private Connection connection = null;
    
    @FXML
    private void gotLogIn(ActionEvent event) throws IOException {
              Parent window1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage window1Stage;
                Scene window1Scene = new Scene(window1, 1004, 425);
                window1Stage = Mocktest.parentWindow;
                window1Stage.setScene(window1Scene);
    }
    
     @FXML
    private void handleCreateUser(ActionEvent event) throws SQLException, IOException {
        
        try {
            connection = dbconnect.connectToDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String newUserName = txt_username_up.getText();
        String newUserEmail = txt_email_up.getText();
        String newUserPassword = txt_password_up.getText();


        String insertQuery = "INSERT INTO users (userName, userPass, userEmail, userType) VALUES (?,?,?,'student')";
        
        PreparedStatement queryStatement = connection.prepareStatement(insertQuery);

        queryStatement.setString(1, newUserName);
        queryStatement.setString(2, newUserPassword);        
        queryStatement.setString(3, newUserEmail);

        int rowCount = queryStatement.executeUpdate();
        
        if(rowCount == 1) {
            System.out.println("User Creation Successful!");
            JOptionPane.showMessageDialog(null,"User: "+ newUserName + 
                    " Created Successfully!");
            
                Parent window1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage window1Stage;
                Scene window1Scene = new Scene(window1, 1004, 425);
                window1Stage = Mocktest.parentWindow;
                window1Stage.setScene(window1Scene);
            
            
        } else {
             System.out.println("User Creation failed!");
            JOptionPane.showMessageDialog(null,"User: "+ newUserName + 
                    " creation failed!");
        }
        
        connection.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
