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
import javafx.stage.Stage;
import mocktest.config.dbconnect;
import mocktest.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserDashBoardController implements Initializable {
    
     private Connection connection = null;

    @FXML
     private void deleteUser(ActionEvent event) throws SQLException, IOException {
         try {
            connection = dbconnect.connectToDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         String insertQuery = "DELETE FROM users WHERE userId = ? ";
         PreparedStatement queryStatement = connection.prepareStatement(insertQuery);
          int id = FXMLDocumentController.userId;
         queryStatement.setString(1,String.valueOf(id));
        int numDelete = queryStatement.executeUpdate();
        System.out.println("" + numDelete + " items were removed from PALLET");
        
                Parent window1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage window1Stage;
                Scene window1Scene = new Scene(window1, 1004, 425);
                window1Stage = Mocktest.parentWindow;
                window1Stage.setScene(window1Scene);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
