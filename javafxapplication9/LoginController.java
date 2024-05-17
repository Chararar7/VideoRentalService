/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication9;


import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ALI CHARARA
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label errorlogin;

    

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    protected static User user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void login(ActionEvent event) throws SQLException, IOException{
      
      String query = "SELECT * FROM users WHERE username = ? AND password = ?";
       PreparedStatement stmt = (PreparedStatement) dbconnection.Connect().prepareStatement(query);
       if (username.getText().isEmpty()) {
    System.out.println("Username field is empty");
} else {
    System.out.println(username.getText());
}
       stmt.setString(1, username.getText());

            stmt.setString(2, password.getText());
             System.out.println(password.getText());
            ResultSet rs = stmt.executeQuery();
            
               if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String creditCardNumber = rs.getString("credit_card_number");
                int type=rs.getInt("type");
                user = new User(id, username.getText(), password.getText(), email, creditCardNumber,type);
                
                
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
            }
               else{ 
                  errorlogin.setText("wong username or password");
               System.out.println("a7mad");
                      
               }
         
              
    


        
    } 
    
}
