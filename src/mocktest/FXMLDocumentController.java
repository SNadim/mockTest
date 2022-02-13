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
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mocktest.config.dbconnect;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private ComboBox<?> type;
    private Connection connection = null;
    
    public static int userId; 
    
    
    @FXML void goToSignUp(ActionEvent event) throws  IOException {
        Parent window1 = FXMLLoader.load(getClass().getResource("signup.fxml"));
                Stage window1Stage;
                Scene window1Scene = new Scene(window1, 426, 490);
                window1Stage = Mocktest.parentWindow;
                window1Stage.setScene(window1Scene);
        
    }
    
   @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {
        try {
            connection = dbconnect.connectToDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String userEmail = txt_email.getText();
        String userpassword = txt_password.getText();

        String loginQuery = "select * from users where userEmail = ? and userPass = ?";

        PreparedStatement queryStatement = connection.prepareStatement(loginQuery);

        queryStatement.setString(1, userEmail);
        queryStatement.setString(2, userpassword);

        ResultSet loginResult = queryStatement.executeQuery();

        if(loginResult.next()) {
            
            
            System.out.println("Login Successful!");
            JOptionPane.showMessageDialog(null,"Login Successful!");
             String query = "SELECT userId FROM users WHERE userEmail = ? and userPass = ?";
                queryStatement = connection.prepareStatement(query);
                queryStatement.setString(1, userEmail);
                queryStatement.setString(2, userpassword);
                ResultSet re1 = queryStatement.executeQuery();
                while (re1.next()) {
                    
                    userId = re1.getInt("userId");
                }
            
            Parent window1;
            try {
                window1 = FXMLLoader.load(getClass().getResource("userDashBoard.fxml"));
                Stage window1Stage;
                Scene window1Scene = new Scene(window1, 600, 500);
                window1Stage = Mocktest.parentWindow;
                window1Stage.setScene(window1Scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login Is Unsuccessful!");
            JOptionPane.showMessageDialog(null,"Login Failed!");
        }
        
        connection.close();
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
